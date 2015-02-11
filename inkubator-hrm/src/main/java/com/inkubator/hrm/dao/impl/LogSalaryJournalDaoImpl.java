package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogSalaryJournalDao;
import com.inkubator.hrm.entity.LogSalaryJournal;

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
	public List<LogSalaryJournal> getAllDataReportGroupingByPeriod(int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		return criteria.list();
	}

	@Override
	public List<LogSalaryJournal> getAllDataReportByParam(Long periodId, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		return criteria.list();
	}

	@Override
	public Long getTotalReportGroupingByPeriod() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalReportByParam(Long periodId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
