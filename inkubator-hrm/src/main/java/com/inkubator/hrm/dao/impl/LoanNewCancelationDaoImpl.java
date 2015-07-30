/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewCancelationDao;
import com.inkubator.hrm.entity.LoanNewCancelation;
import com.inkubator.hrm.web.model.LoanNewCancelationBoxViewModel;
import com.inkubator.hrm.web.search.LoanNewCancelationBoxSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewCancelationDao")
@Lazy
public class LoanNewCancelationDaoImpl extends IDAOImpl<LoanNewCancelation> implements LoanNewCancelationDao {

    @Override
    public Class<LoanNewCancelation> getEntityClass() {
        return LoanNewCancelation.class;
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<LoanNewCancelationBoxViewModel> getByParam(LoanNewCancelationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        StringBuffer selectQuery = new StringBuffer(
    			"SELECT DISTINCT (cancellation.id) AS cancelationId, " +
                        "cancellation.loan_cancellation_number AS cancelationCode, " +
                        "application.nomor AS applicationCode," +
                        "loanNewType.loan_type_name AS loanNewTypeName, " +
                        "application.nominal_principal AS nominal, " +
                        "cancellation.cancelation_date AS cancelationDate, " +
                        "application.application_date AS applicationDate, " +
                        "cancellation.reason AS reason, " +
                        "CONCAT(bioData.first_name,' ',bioData.last_name) AS employeeName " +
                        "FROM loan_new_cancelation cancellation " +
                        "LEFT JOIN loan_new_application AS application ON application.id = cancellation.loan_id " +
                        "LEFT JOIN loan_new_type AS loanNewType ON loanNewType.id = application.loan_new_type " +
                        "LEFT JOIN log_approver_history AS approverHistory ON approverHistory.activity_number = application.approval_activity_number \n" +
                        "LEFT JOIN hrm_user AS approver ON approver.id = approverHistory.approver_id " +
                        "LEFT JOIN hrm_user AS user ON user.emp_data_id = application.emp_id " +
                        "LEFT JOIN emp_data AS empData ON empData.id = user.emp_data_id " +
                        "LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " );
        
    	selectQuery.append(this.setWhereQueryByParam(parameter));
    	selectQuery.append("ORDER BY " + orderable);
        
    	   Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString())
    			.setMaxResults(maxResults)
    			.setFirstResult(firstResult)
    			.setResultTransformer(Transformers.aliasToBean(LoanNewCancelationBoxViewModel.class));
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return hbm.list();
    }

    @Override
    public Long getTotalByParam(LoanNewCancelationBoxSearchParameter parameter) {  
        
         StringBuffer selectQuery = new StringBuffer(
    			"SELECT COUNT(DISTINCT(cancellation.id)) " +                        
                        "FROM loan_new_cancelation cancellation " +
                        "LEFT JOIN loan_new_application AS application ON application.id = cancellation.loan_id " +
                        "LEFT JOIN loan_new_type AS loanNewType ON loanNewType.id = application.loan_new_type " +
                        "LEFT JOIN log_approver_history AS approverHistory ON approverHistory.activity_number = application.approval_activity_number \n" +
                        "LEFT JOIN hrm_user AS approver ON approver.id = approverHistory.approver_id " +
                        "LEFT JOIN hrm_user AS user ON user.emp_data_id = application.emp_id " +
                        "LEFT JOIN emp_data AS empData ON empData.id = user.emp_data_id " +
                        "LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " );
         
    	selectQuery.append(this.setWhereQueryByParam(parameter));
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return Long.valueOf(hbm.uniqueResult().toString());
    }
    
    private String setWhereQueryByParam(LoanNewCancelationBoxSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getCodeApplication())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("application.nomor LIKE :codeApplication ");
        }        
        if (StringUtils.isNotEmpty(parameter.getCodeCancelation())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("cancellation.loan_cancellation_number LIKE :codeCancellation ");
        }        
        if (StringUtils.isNotEmpty(parameter.getLoanNewTypeName())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("loanNewType.loan_type_name LIKE :loanNewTypeName ");
        }    
        if (StringUtils.isNotEmpty(parameter.getEmpName())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("(bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName) ");
        }        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("(user.user_id = :userId OR approver.user_id = :userId) ");
        }
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryByParam(Query hbm, LoanNewCancelationBoxSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "codeApplication")){
    			hbm.setParameter("codeApplication", "%" + parameter.getCodeApplication() + "%");
    		} else if(StringUtils.equals(param, "codeCancellation")){
    			hbm.setParameter("codeCancellation", "%" + parameter.getCodeCancelation() + "%");
    		} else if(StringUtils.equals(param, "loanNewTypeName")){
    			hbm.setParameter("loanNewTypeName", "%" + parameter.getLoanNewTypeName()+ "%");
    		} else if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		}
    	}    	
    	return hbm;
    }
    
}
