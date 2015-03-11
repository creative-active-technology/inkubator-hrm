package com.inkubator.hrm.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogUnregPayrollDao;
import com.inkubator.hrm.entity.LogUnregPayroll;
import com.inkubator.hrm.web.model.UnregPayrollViewModel;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logUnregPayrollDao")
@Lazy
public class LogUnregPayrollDaoImpl extends IDAOImpl<LogUnregPayroll> implements LogUnregPayrollDao {

	@Override
	public Class<LogUnregPayroll> getEntityClass() {
		return LogUnregPayroll.class;
		
	}

	@Override
	public void deleteByUnregSalaryId(Long unregSalaryId) {
		Query query = getCurrentSession().createQuery("delete from LogUnregPayroll where unregSalaryId = :unregSalaryId")
				.setLong("unregSalaryId", unregSalaryId);
        query.executeUpdate();
	}

	@Override
	public Long getTotalEmployeeByUnregSalaryId(Long unregSalaryId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(distinct empDataId) "
    			+ "FROM LogUnregPayroll "
    			+ "WHERE unregSalaryId = :unregSalaryId");
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setParameter("unregSalaryId", unregSalaryId);
    	
    	return new Long(hbm.uniqueResult().toString());
		
	}

	@Override
	public BigDecimal getTotalTakeHomePayByUnregSalaryId(Long unregSalaryId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) "
    			+ "FROM LogUnregPayroll "
    			+ "WHERE unregSalaryId = :unregSalaryId");
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setParameter("unregSalaryId", unregSalaryId);
    	
    	return new BigDecimal(hbm.uniqueResult().toString());
	}
    
    @Override
    public List<UnregPayrollViewModel> getByParam(UnregPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT id as id, "
    			+ "empNik as empNik, "
    			+ "empName as empName, "
    			+ "SUM(CASE WHEN factor = 1 THEN nominal ELSE 0.0 END) AS income, "
    			+ "SUM(CASE WHEN factor = -1 THEN nominal ELSE 0.0 END) AS deduction, "
    			+ "SUM(CASE WHEN modelCompSpecific = 2 THEN nominal ELSE 0.0 END) AS tax, "
    			+ "SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) AS takeHomePay "
    			+ "FROM LogUnregPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	selectQuery.append("GROUP BY unregSalaryId,empDataId ");
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(UnregPayrollViewModel.class));
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    @Override
    public Long getTotalByParam(UnregPayrollSearchParameter parameter) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(distinct empDataId) "
    			+ "FROM LogUnregPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String getWhereQueryByParam(UnregPayrollSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty(parameter.getName())) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empName LIKE :empName ");
        }
    	
        if (StringUtils.isNotEmpty(parameter.getNik())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("empNik LIKE :empNik ");
        }
        
        if (parameter.getUnregSalaryId() != null) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("unregSalaryId = :unregSalaryId ");
        }
        
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryByParam(Query hbm, UnregPayrollSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getNik() + "%");
    		} else if(StringUtils.equals(param, "unregSalaryId")){
    			hbm.setParameter("unregSalaryId", parameter.getUnregSalaryId());
    		}
    	}
    	
    	return hbm;
    }

	

}
