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
import com.inkubator.hrm.dao.LogMonthEndPayrollDao;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logMonthEndPayrollDao")
@Lazy
public class LogMonthEndPayrollDaoImpl extends IDAOImpl<LogMonthEndPayroll> implements LogMonthEndPayrollDao {

    @Override
    public Class<LogMonthEndPayroll> getEntityClass() {
        return LogMonthEndPayroll.class;
    }

    @Override
    public BigDecimal getTotalTakeHomePayByPeriodeId(Long periodeId){
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) "
    			+ "FROM LogMonthEndPayroll "
    			+ "WHERE periodeId = :periodeId");
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setParameter("periodeId", periodeId);
    	
    	return new BigDecimal(hbm.uniqueResult().toString());
    }
    
    @Override
    public List<LogMonthEndPayrollViewModel> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT id as id, "
    			+ "empNik as empNik, "
    			+ "empName as empName, "
    			+ "SUM(CASE WHEN factor = 1 THEN nominal ELSE 0.0 END) AS income, "
    			+ "SUM(CASE WHEN factor = -1 THEN nominal ELSE 0.0 END) AS deduction, "
    			+ "SUM(CASE WHEN factor = 0 AND modelCompSpecific != 100 THEN nominal ELSE 0.0 END) AS subsidy, "
    			+ "SUM(CASE WHEN modelCompSpecific = 100 THEN nominal ELSE 0.0 END) AS takeHomePay "
    			+ "FROM LogMonthEndPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	selectQuery.append("GROUP BY empDataId ");
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(LogMonthEndPayrollViewModel.class));
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    @Override
    public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) {
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(distinct empDataId) "
    			+ "FROM LogMonthEndPayroll ");    	
    	selectQuery.append(this.getWhereQueryByParam(parameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String getWhereQueryByParam(LogMonthEndPayrollSearchParameter parameter) {
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
        
        if (parameter.getPeriodeId() != null) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("periodeId = :periodeId ");
        }
        
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQueryByParam(Query hbm, LogMonthEndPayrollSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getNik() + "%");
    		} else if(StringUtils.equals(param, "periodeId")){
    			hbm.setParameter("periodeId", parameter.getPeriodeId());
    		}
    	}
    	
    	return hbm;
    }

	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogMonthEndPayroll temp WHERE temp.periodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
		
	}
    
}
