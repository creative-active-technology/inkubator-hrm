package com.inkubator.hrm.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogMonthEndTaxesDao;
import com.inkubator.hrm.entity.LogMonthEndTaxes;
import com.inkubator.hrm.web.model.PphReportModel;
import com.inkubator.hrm.web.search.LogMonthEndTaxesSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;

/**
*
* @author rizkykojek
*/
@Repository(value = "logMonthEndTaxesDao")
@Lazy
public class LogMonthEndTaxesDaoImpl extends IDAOImpl<LogMonthEndTaxes> implements LogMonthEndTaxesDao {

	@Override
	public Class<LogMonthEndTaxes> getEntityClass() {
		return LogMonthEndTaxes.class;
	}

	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogMonthEndTaxes temp WHERE temp.periodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
	}

    @Override
    public List<PphReportModel> getAllDataByParam(LogMonthEndTaxesSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        final StringBuilder query = new StringBuilder("select id as id, empDataId as empDataId, empName as empName,"
                + "empNik as empNik,"
                + "empGolJabatan as empGolJabatan,"
                + "empNik as empNik,"
                + "SUM(CASE WHEN taxCompId = 10 THEN nominal END) as biayaJabatan,"
                + "SUM(CASE WHEN taxCompId = 2 THEN nominal END) as pph,"
                + "SUM(CASE WHEN taxCompId = 17 THEN nominal END) as ptkp"
                + " from LogMonthEndTaxes A ");
        query.append(this.getWhereQueryByParamForReportPPh(searchParameter));
        
        query.append(" GROUP BY empNik");
    
        Query hbm = getCurrentSession().createQuery(query.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
            	.setResultTransformer(Transformers.aliasToBean(PphReportModel.class));
        hbm = this.setValueQueryByParamForReportSalaryNote(hbm, searchParameter);
	
        return hbm.list();
        /*return getCurrentSession().createQuery(query.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PphReportModel.class))
                    .list();*/
    }

    @Override
    public Long getTotalDataByParam(LogMonthEndTaxesSearchParameter searchParameter) {
        /*final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(LMTE.id)");
        query.append(" FROM log_month_end_taxes LMTE");
        query.append(" GROUP BY LMTE.id)");
        query.append(" as totalData");
        
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());*/
    	StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(distinct empDataId) "
    			+ "FROM LogMonthEndTaxes ");    	
    	selectQuery.append(this.getWhereQueryByParamForReportPPh(searchParameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryByParamForReportSalaryNote(hbm, searchParameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }
    
    private String getWhereQueryByParamForReportPPh(LogMonthEndTaxesSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (!parameter.getListGolJab().isEmpty()) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empGolJabatan IN (:empGolJabatan) ");
        }    	
    	if (!parameter.getListDepartment().isEmpty()) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("departmentId IN (:departmentId) ");
        }    
    	if (!parameter.getListEmpType().isEmpty()) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("empTypeId IN (:empTypeId) ");
        }
        if (parameter.getPeriodeId() != null) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("periodeId = :periodeId ");
        }       
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    } 
    
private Query setValueQueryByParamForReportSalaryNote(Query hbm, LogMonthEndTaxesSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empGolJabatan")){
    			hbm.setParameterList("empGolJabatan", parameter.getListGolJab());
    		} else if(StringUtils.equals(param, "empTypeId")){
    			hbm.setParameterList("empTypeId", parameter.getListEmpType());
    		} else if(StringUtils.equals(param, "departmentId")){
    			hbm.setParameterList("departmentId", parameter.getListDepartment());
    		} else if(StringUtils.equals(param, "periodeId")){
    			hbm.setParameter("periodeId", parameter.getPeriodeId());
    		}
    	}
    	return hbm;
	}
}
