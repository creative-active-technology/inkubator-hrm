package com.inkubator.hrm.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.RmbsApplicationDao;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsApplicationDao")
@Lazy
public class RmbsApplicationDaoImpl extends IDAOImpl<RmbsApplication> implements RmbsApplicationDao {

	@Override
	public Class<RmbsApplication> getEntityClass() {
		return RmbsApplication.class;
		
	}

	@Override
	public BigDecimal getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(Long empDataId, Long rmbsTypeId, Date startDate, Date endDate) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(nominal) " +
    			"FROM RmbsApplication " +
    			"WHERE empData.id = :empDataId " +
    			"AND rmbsType.id = :rmbsTypeId " +
    			"AND applicationDate >= :startDate " +
    			"AND applicationDate <= :endDate " +
    			"AND applicationStatus IN (:applicationStatus)");
		
		List<Integer> applicationStatus =  new ArrayList<Integer>();
		applicationStatus.add(HRMConstant.RMBS_APPLICATION_STATUS_DISBURSED);
		applicationStatus.add(HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED);
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("empDataId", empDataId)
    			.setParameter("rmbsTypeId", rmbsTypeId)
    			.setParameter("startDate", startDate)
    			.setParameter("endDate", endDate)
    			.setParameterList("applicationStatus", applicationStatus);
    	
    	Object obj = hbm.uniqueResult();
    	return obj != null ? new BigDecimal(obj.toString()) : new BigDecimal(0);
		
	}
	
	@Override
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT approvalActivity.id AS approvalActivityId, " +
    			"rmbsApplication.id AS rmbsApplicationId, " +
    			"empData.nik AS empNik," +
    			"CONCAT(bioData.first_name,' ',bioData.last_name) AS empName, " +
    			"rmbsType.id AS rmbsTypeId, " +
    			"rmbsType.name AS rmbsTypeName, " +
    			"approvalActivity.approval_status AS approvalStatus, " +
    			"approvalActivity.pending_data AS jsonData, " +
    			"rmbsApplication.code AS rmbsApplicationCode " +
    			"FROM approval_activity approvalActivity " +
    			"LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
    			"LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " +
    			"LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
    			"LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
    			"INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
                "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
                "INNER JOIN company AS company ON department.company_id = company.id  " +
    			"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
    			"LEFT JOIN rmbs_type AS rmbsType ON approvalActivity.type_specific = rmbsType.id " +
    			"LEFT JOIN rmbs_application AS rmbsApplication ON approvalActivity.activity_number = rmbsApplication.approval_activity_number " +
    			"WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) " +
    			"AND (rmbsApplication.application_status = 0 OR rmbsApplication.application_status IS NULL) " +
    			"AND approvalDefinition.name = :appDefinitionName "
    			+ " AND  company.id = :companyId ");    	
    	selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
    	selectQuery.append("GROUP BY approvalActivity.activity_number ");
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(RmbsApplicationUndisbursedViewModel.class));
    	hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    @Override
    public Long getTotalUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) " +
    			"FROM approval_activity approvalActivity " +
    			"LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
    			"LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " +
    	    	"LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
    	    	"LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
    	    	"INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
                "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
                "INNER JOIN company AS company ON department.company_id = company.id  " +
    	    	"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
    	    	"LEFT JOIN rmbs_type AS rmbsType ON approvalActivity.type_specific = rmbsType.id " +
    	    	"LEFT JOIN rmbs_application AS rmbsApplication ON approvalActivity.activity_number = rmbsApplication.approval_activity_number " +
    	    	"WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) " +
    	    	"AND (rmbsApplication.application_status = 0 OR rmbsApplication.application_status IS NULL) " +
    	    	"AND approvalDefinition.name = :appDefinitionName"
    	    	+ " AND  company.id = :companyId ");    	
    	selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String setWhereQueryUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getEmpNik())) {
        	whereQuery.append("AND empData.nik LIKE :empNik ");
        }        
        if (StringUtils.isNotEmpty(parameter.getEmpName())) {
        	whereQuery.append("AND (bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName) ");
        }        
        if (StringUtils.isNotEmpty(parameter.getRmbsType())) {
        	whereQuery.append("AND rmbsType.name LIKE :rmbsType ");
        }    
        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
        	whereQuery.append("AND (requester.user_id = :userId AND approvalActivity.approval_status IN (0,1,6) " +
        			"OR approver.user_id = :userId AND approvalActivity.approval_status IN (0)) ");
        } else {
        	//view for administrator(can view all employee)
        	whereQuery.append("AND approvalActivity.approval_status IN (0,1,6) ");
        }
        
        return whereQuery.toString();
    }
    
    private Query setValueQueryUndisbursedActivityByParam(Query hbm, RmbsApplicationUndisbursedSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getEmpNik() + "%");
    		} else if(StringUtils.equals(param, "rmbsType")){
    			hbm.setParameter("rmbsType", "%" + parameter.getRmbsType() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		} else if(StringUtils.equals(param, "appDefinitionName")){
    			hbm.setParameter("appDefinitionName", HRMConstant.REIMBURSEMENT);
    		}else if(StringUtils.equals(param, "companyId")){
    			hbm.setParameter("companyId", HrmUserInfoUtil.getCompanyId());
    		}
    	}    	
    	return hbm;
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }

	@Override
	public RmbsApplication getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		criteria.setFetchMode("rmbsType", FetchMode.JOIN);
		criteria.setFetchMode("currency", FetchMode.JOIN);
		return (RmbsApplication) criteria.uniqueResult();
	}

	@Override
	public List<RmbsApplication> getUndisbursedByParam(int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("applicationStatus", HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED));
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		criteria.setFetchMode("rmbsType", FetchMode.JOIN);
		criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalUndisbursedByParam() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("applicationStatus", HRMConstant.RMBS_APPLICATION_STATUS_UNDISBURSED));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
}
