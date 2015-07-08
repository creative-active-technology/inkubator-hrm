package com.inkubator.hrm.dao.impl;

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
    			if(searchParameter.getName() != null){
					query.append("WHERE empName like :name ");
    			}else if(searchParameter.getGoljab() != null){
					query.append("WHERE empGolJabatan like :name ");
    			}
                query.append("GROUP BY empNik");
                if(searchParameter.getName() != null){
                	return getCurrentSession().createQuery(query.toString())
                			.setParameter("name", '%' + searchParameter.getName() + '%')
                            .setMaxResults(maxResults).setFirstResult(firstResult)
                            .setResultTransformer(Transformers.aliasToBean(PphReportModel.class))
                            .list();
    			}else if(searchParameter.getGoljab() != null){
    				return getCurrentSession().createQuery(query.toString())
                			.setParameter("name", '%' + searchParameter.getGoljab() + '%')
                            .setMaxResults(maxResults).setFirstResult(firstResult)
                            .setResultTransformer(Transformers.aliasToBean(PphReportModel.class))
                            .list();
    			}else{
    				return getCurrentSession().createQuery(query.toString())
    	                    .setMaxResults(maxResults).setFirstResult(firstResult)
    	                    .setResultTransformer(Transformers.aliasToBean(PphReportModel.class))
    	                    .list();
    			}
    }

    @Override
    public Long getTotalDataByParam(LogMonthEndTaxesSearchParameter searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(LMTE.id)");
        query.append(" FROM log_month_end_taxes LMTE");
        if(searchParameter.getName() != null){
			query.append(" WHERE LMTE.emp_name like :name ");
		}else if(searchParameter.getGoljab() != null){
			query.append(" WHERE LMTE.emp_gol_jabatan like :name ");
		}
        query.append(" GROUP BY LMTE.id) as totalData");
        Query hbm;
        if(searchParameter.getName() != null){
        	hbm = getCurrentSession().createSQLQuery(query.toString()).setParameter("name", '%' + searchParameter.getName() + '%');
		}else if(searchParameter.getGoljab() != null){
			hbm = getCurrentSession().createSQLQuery(query.toString()).setParameter("name", '%' + searchParameter.getGoljab() + '%');
		}else{
			hbm = getCurrentSession().createSQLQuery(query.toString());
		}
        return Long.valueOf(hbm.uniqueResult().toString());
    }
}
