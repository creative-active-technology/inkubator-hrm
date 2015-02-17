package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogSalaryJournalDao;
import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.web.model.ReportSalaryJounalModel;

/**
*
* @author rizkykojek
*/
@Repository(value = "logSalaryJournalDao")
@Lazy
public class LogSalaryJournalDaoImpl extends IDAOImpl<LogSalaryJournal> implements LogSalaryJournalDao {

	@Override
	public Class<LogSalaryJournal> getEntityClass() {		
		return LogSalaryJournal.class;
	}
	
	@Override
	public void deleteByPeriodId(Long periodeId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogSalaryJournal temp WHERE temp.wtPeriode.id = :periodeId")
				.setLong("periodeId", periodeId);
        query.executeUpdate();
		
	}

	@Override
	public List<ReportSalaryJounalModel> getAllDataReportGroupingByPeriod(int firstResult, int maxResults, Order orderable) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT wtPeriode.id as periodId, "
				+ "wtPeriode.fromPeriode as payrollPeriod, "
				+ "wtPeriode.payrollDate as payrollDate, "
				+ "SUM(credit) as balance ");
		query.append("FROM LogSalaryJournal ");
		query.append("GROUP BY wtPeriode ");
		query.append("ORDER BY " + orderable);
		
		Query hbm = getCurrentSession().createQuery(query.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
            	.setResultTransformer(Transformers.aliasToBean(ReportSalaryJounalModel.class));
		return hbm.list();
	}

	@Override
	public List<LogSalaryJournal> getAllDataReportByParam(Long periodId, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("wtPeriode.id", periodId));
		criteria.addOrder(orderable);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalReportGroupingByPeriod() {
		StringBuffer query = new StringBuffer();
		query.append("SELECT count(distinct wtPeriode.id) ");
		query.append("FROM LogSalaryJournal ");    	
    	
    	Query hbm = getCurrentSession().createQuery(query.toString());     	
        return Long.valueOf(hbm.uniqueResult().toString());
	}

	@Override
	public Long getTotalReportByParam(Long periodId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("wtPeriode.id", periodId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
