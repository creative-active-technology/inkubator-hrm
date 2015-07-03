package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FingerSwapCapturedDao;
import com.inkubator.hrm.entity.FingerSwapCaptured;
import com.inkubator.hrm.web.model.FingerSwapCapturedViewModel;
import com.inkubator.hrm.web.search.FingerSwapCapturedSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "fingerSwapCapturedDao")
@Lazy
public class FingerSwapCapturedDaoImpl extends IDAOImpl<FingerSwapCaptured> implements FingerSwapCapturedDao {

	@Override
	public Class<FingerSwapCaptured> getEntityClass() {
		return FingerSwapCaptured.class;
		
	}

	@Override
	public List<FingerSwapCaptured> getAllDataByFingerIndexIdAndSwapDatetimeLogBetween(List<String> fingerIndexIds, Date startDate, Date endDate, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.in("fingerIndexId", fingerIndexIds));
		criteria.add(Restrictions.ge("swapDatetimeLog", startDate));
		criteria.add(Restrictions.le("swapDatetimeLog", endDate));
		criteria.addOrder(order);
		return criteria.list();
	}

	@Override
	public List<FingerSwapCapturedViewModel> getAllDataByParam(FingerSwapCapturedSearchParameter parameter, int firstResult, int maxResults, Order orderable){
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT fingerSwapCaptured.id AS id, " +
    			"fingerSwapCaptured.swap_datetime_log AS fingerSwapDate, " +
    			"empData.nik AS nik, " +
    			"concat(bioData.first_name, ' ', bioData.last_name) AS employeeName, " +
    			"machineFinger.name AS machineFingerName  " +
    			"FROM finger_swap_captured AS fingerSwapCaptured " +
    			"INNER JOIN finger_match_emp AS fingerMatchEmp ON fingerMatchEmp.finger_index_id=fingerSwapCaptured.finger_index_id AND fingerMatchEmp.mecine_finger_id=fingerSwapCaptured.mecine_finger_id " +
    			"INNER JOIN emp_data AS empData ON fingerMatchEmp.emp_data_id=empData.id " +
    			"INNER JOIN bio_data AS bioData ON empData.bio_data_id=bioData.id " +
    			"INNER JOIN mecine_finger AS machineFinger ON fingerSwapCaptured.mecine_finger_id=machineFinger.id ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(FingerSwapCapturedViewModel.class));
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return hbm.list(); 
	}

	@Override
    public Long getTotalByParam(FingerSwapCapturedSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
				"SELECT COUNT(fingerSwapCaptured.id) " +
		    	"FROM finger_swap_captured AS fingerSwapCaptured " +
		    	"INNER JOIN finger_match_emp AS fingerMatchEmp ON fingerMatchEmp.finger_index_id=fingerSwapCaptured.finger_index_id AND fingerMatchEmp.mecine_finger_id=fingerSwapCaptured.mecine_finger_id " +
		    	"INNER JOIN emp_data AS empData ON fingerMatchEmp.emp_data_id=empData.id " +
		    	"INNER JOIN bio_data AS bioData ON empData.bio_data_id=bioData.id " +
		    	"INNER JOIN mecine_finger AS machineFinger ON fingerSwapCaptured.mecine_finger_id=machineFinger.id ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }
	
	private String getWhereQueryByParam(FingerSwapCapturedSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (parameter.getStartPeriod() != null) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("fingerSwapCaptured.swap_datetime_log >= :startDate ");
        }
    	
    	if (parameter.getEndPeriod() != null) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("fingerSwapCaptured.swap_datetime_log <= :endDate ");
        }
    	
        if (parameter.getEmpData() != null) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("empData.id = :empDataId ");
        }
        
        if (parameter.getMachineFingerId() != null && parameter.getMachineFingerId() != 0) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("fingerSwapCaptured.mecine_finger_id = :machineFingerId ");
        }
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryByParam(Query hbm, FingerSwapCapturedSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "startDate")){
    			hbm.setParameter("startDate", parameter.getStartPeriod());
    		} else if(StringUtils.equals(param, "endDate")){
    			DateTime dtEndPeriod = new DateTime(parameter.getEndPeriod()).plusHours(23).plusMinutes(59).plusSeconds(59);
    			hbm.setParameter("endDate", dtEndPeriod.toDate());
    		} else if(StringUtils.equals(param, "empDataId")){
    			hbm.setParameter("empDataId", parameter.getEmpData().getId());
    		} else if(StringUtils.equals(param, "machineFingerId")){
    			hbm.setParameter("machineFingerId", parameter.getMachineFingerId());
    		}
    	}
    	
    	return hbm;
    }
	
}
