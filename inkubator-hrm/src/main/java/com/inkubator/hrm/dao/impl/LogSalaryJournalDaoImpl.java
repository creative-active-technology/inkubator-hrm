package com.inkubator.hrm.dao.impl;

import org.hibernate.Query;
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
		Query query = getCurrentSession().createQuery("DELETE FROM LogSalaryJournal temp WHERE temp.periodeId = :periodeId")
				.setLong("periodeId", periodeId);
        query.executeUpdate();
		
	}

}
