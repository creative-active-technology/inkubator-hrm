package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsCancelationDao;
import com.inkubator.hrm.entity.RmbsCancelation;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.RmbsCancelationSearchParameter;

/**
*
* @author rizkykojek
*/
@Repository(value = "rmbsCancelationDao")
@Lazy
public class RmbsCancelationDaoImpl extends IDAOImpl<RmbsCancelation> implements RmbsCancelationDao {

	@Override
	public Class<RmbsCancelation> getEntityClass() {
		
		return RmbsCancelation.class;
	}

	@Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }

	@Override
	public List<RmbsCancelationViewModel> getByParam(RmbsCancelationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT DISTINCT (cancellation.id) AS cancelationId, " +
    			"cancellation.code AS cancelationCode, " +
    			"application.code AS applicationCode, " +
    			"rmbsType.name AS rmbsTypeName, " +
    			"application.nominal AS nominal, " +
    			"cancellation.cancelled_date AS cancelationDate, " +
    			"application.application_date AS applicationDate, " +
    			"cancellation.reason AS reason, " +
    			"CONCAT(bioData.first_name,' ',bioData.last_name) AS employeeName " +
    			"FROM rmbs_cancelation cancellation " +
    			"LEFT JOIN rmbs_application AS application ON application.id = cancellation.rmbs_application_id " +
    			"LEFT JOIN rmbs_type AS rmbsType ON rmbsType.id = application.rmbs_type_id " +
    			"LEFT JOIN log_approver_history AS approverHistory ON approverHistory.activity_number = application.approval_activity_number " +
    			"LEFT JOIN hrm_user AS approver ON approver.id = approverHistory.approver_id " +
    			"LEFT JOIN hrm_user AS user ON user.emp_data_id = application.emp_data_id " +
    			"LEFT JOIN emp_data AS empData ON empData.id = user.emp_data_id " +
    			"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " );    	
    	selectQuery.append(this.setWhereQueryByParam(parameter));
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString())
    			.setMaxResults(maxResults)
    			.setFirstResult(firstResult)
    			.setResultTransformer(Transformers.aliasToBean(RmbsCancelationViewModel.class));
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return hbm.list();
	}

	@Override
	public Long getTotalByParam(RmbsCancelationSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT COUNT(DISTINCT(cancellation.id)) " +
    			"FROM rmbs_cancelation cancellation " +
    			"LEFT JOIN rmbs_application AS application ON application.id = cancellation.rmbs_application_id " +
    			"LEFT JOIN rmbs_type AS rmbsType ON rmbsType.id = application.rmbs_type_id " +
    			"LEFT JOIN log_approver_history AS approverHistory ON approverHistory.activity_number = application.approval_activity_number " +
    			"LEFT JOIN hrm_user AS approver ON approver.id = approverHistory.approver_id " +
    			"LEFT JOIN hrm_user AS user ON user.emp_data_id = application.emp_data_id " +
    			"LEFT JOIN emp_data AS empData ON empData.id = user.emp_data_id " +
    			"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " );    	
    	selectQuery.append(this.setWhereQueryByParam(parameter));
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String setWhereQueryByParam(RmbsCancelationSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getCodeApplication())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("application.code LIKE :codeApplication ");
        }        
        if (StringUtils.isNotEmpty(parameter.getCodeCancelation())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("cancellation.code LIKE :codeCancellation ");
        }        
        if (StringUtils.isNotEmpty(parameter.getRmbsType())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("rmbsType.name LIKE :rmbsType ");
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
    
    private Query setValueQueryByParam(Query hbm, RmbsCancelationSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "codeApplication")){
    			hbm.setParameter("codeApplication", "%" + parameter.getCodeApplication() + "%");
    		} else if(StringUtils.equals(param, "codeCancellation")){
    			hbm.setParameter("codeCancellation", "%" + parameter.getCodeCancelation() + "%");
    		} else if(StringUtils.equals(param, "rmbsType")){
    			hbm.setParameter("rmbsType", "%" + parameter.getRmbsType() + "%");
    		} else if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		}
    	}    	
    	return hbm;
    }

}
