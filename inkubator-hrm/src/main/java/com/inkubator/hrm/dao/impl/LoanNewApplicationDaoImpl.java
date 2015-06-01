/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewApplicationDao")
@Lazy
public class LoanNewApplicationDaoImpl extends IDAOImpl<LoanNewApplication> implements LoanNewApplicationDao {

    @Override
    public Class<LoanNewApplication> getEntityClass() {
        return LoanNewApplication.class;
    }

    @Override
    public LoanNewApplication getEntityByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return (LoanNewApplication) criteria.uniqueResult();
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<LoanNewApplication> getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewType.id", loanNewTypeId));
        criteria.add(Restrictions.ne("loanStatus", HRMConstant.LOAN_PAID));
        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchemaId));

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_DISBURSED));
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_PAID));
        criteria.add(disjunction);

        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getByParamByStatusUndisbursed(LoanNewSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_UNDISBURSED));
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamByStatusUndisbursed(LoanNewSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_UNDISBURSED));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LoanNewSearchParameter parameter, Criteria criteria) {

        if (StringUtils.isNotEmpty(parameter.getLoanType())) {
            criteria.createAlias("loanNewType", "loanNewType", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("loanNewType.loanTypeName", parameter.getLoanType(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
            criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
            criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LoanNewApplicationBoxViewModel> getUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        
        StringBuffer selectQuery = new StringBuffer(
    			"SELECT approvalActivity.id AS approvalActivityId, " +
                        "approvalActivity.activity_number AS activityNumber, " +
                        "loanApplication.id AS loanNewApplicationId, " +
                        "empData.nik AS empNik, " +
                        "CONCAT(bioData.first_name,' ',bioData.last_name) AS empName, " +
                        "empDataApprover.nik AS approverNik, " +
                        "CONCAT(bioDataApprover.first_name,' ',bioDataApprover.last_name) AS approverName, " +
                        "loanType.id AS loanNewTypeId, " +
                        "loanType.loan_type_name AS loanNewTypeName, " +
                        "approvalActivity.approval_status AS approvalStatus, " +
                        "approvalActivity.pending_data AS jsonData " +                     
                        "FROM hrm.approval_activity approvalActivity " +
                        "INNER JOIN hrm.approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
                        "INNER JOIN hrm.hrm_user AS approver ON approver.user_id = approvalActivity.approved_by  " +
                        "INNER JOIN hrm.hrm_user AS requester ON requester.user_id = approvalActivity.request_by  " +
                        "INNER JOIN hrm.emp_data AS empData ON requester.emp_data_id = empData.id  " +
                        "INNER JOIN hrm.bio_data AS bioData ON empData.bio_data_id = bioData.id  " +
                        "INNER JOIN hrm.emp_data AS empDataApprover ON approver.emp_data_id = empDataApprover.id  " +
                        "INNER JOIN hrm.bio_data AS bioDataApprover ON empDataApprover.bio_data_id = bioDataApprover.id  " +
                        "INNER JOIN hrm.loan_new_type AS loanType ON approvalActivity.type_specific = loanType.id  " +
                        "LEFT JOIN hrm.loan_new_application AS loanApplication ON approvalActivity.activity_number = loanApplication.approval_activity_number  " +
                        "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM hrm.approval_activity app GROUP BY app.activity_number)  " +
                        "AND approvalDefinition.name = :appDefinitionName  "); 

        
    	selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
    	selectQuery.append("GROUP BY approvalActivity.activity_number ");
    	selectQuery.append("ORDER BY ").append(orderable);
        
    	   Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(LoanNewApplicationBoxViewModel.class));
    	hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    private String setWhereQueryUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getEmpNik())) {
        	whereQuery.append("AND empData.nik LIKE :empNik ");
        }        
        if (StringUtils.isNotEmpty(parameter.getEmpName())) {
        	whereQuery.append("AND (bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName) ");
        }        
        if (StringUtils.isNotEmpty(parameter.getLoanTypeName())) {
        	whereQuery.append("AND loanType.loan_type_name LIKE :loanTypeName ");
        }    
        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
            whereQuery.append("AND (requester.user_id = :userId AND approvalActivity.approval_status IN (").append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_APPROVED)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_WAITING_REVISED)
                        .append(") ")
                        .append("OR approver.user_id = :userId AND approvalActivity.approval_status IN (")
                        .append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(")) ");
        } else {
        	//view for administrator(can view all employee)        	
                whereQuery.append("AND approvalActivity.approval_status IN ( ").append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_APPROVED)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_WAITING_REVISED)
                        .append(")");
        }
        
        return whereQuery.toString();
    }
    
    private Query setValueQueryUndisbursedActivityByParam(Query hbm, LoanNewApplicationBoxSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getEmpNik() + "%");
    		} else if(StringUtils.equals(param, "loanTypeName")){
    			hbm.setParameter("loanTypeName", "%" + parameter.getLoanTypeName() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		} else if(StringUtils.equals(param, "appDefinitionName")){
    			hbm.setParameter("appDefinitionName", HRMConstant.LOAN);
    		}
    	}    	
    	return hbm;
    }

    @Override
    public Long getTotalUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) " +
    			"FROM hrm.approval_activity approvalActivity " +
                        "INNER JOIN hrm.approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
                        "INNER JOIN hrm.hrm_user AS approver ON approver.user_id = approvalActivity.approved_by  " +
                        "INNER JOIN hrm.hrm_user AS requester ON requester.user_id = approvalActivity.request_by  " +
                        "INNER JOIN hrm.emp_data AS empData ON requester.emp_data_id = empData.id  " +
                        "INNER JOIN hrm.bio_data AS bioData ON empData.bio_data_id = bioData.id  " +
                        "INNER JOIN hrm.emp_data AS empDataApprover ON requester.emp_data_id = empDataApprover.id  " +
                        "INNER JOIN hrm.bio_data AS bioDataApprover ON empDataApprover.bio_data_id = bioDataApprover.id  " +
                        "INNER JOIN hrm.loan_new_type AS loanType ON approvalActivity.type_specific = loanType.id  " +
                        "LEFT JOIN hrm.loan_new_application AS loanApplication ON approvalActivity.activity_number = loanApplication.approval_activity_number  " +
                        "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM hrm.approval_activity app GROUP BY app.activity_number)  " +
                        "AND approvalDefinition.name = :appDefinitionName  ");    	
    	selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }
}
