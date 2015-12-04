package com.inkubator.hrm.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogWtProcessReadFingerDao;
import com.inkubator.hrm.entity.LogWtProcessReadFinger;

/**
*
* @author rizkykojek
*/
@Repository(value = "logWtProcessReadFingerDao")
@Lazy
public class LogWtProcessReadFingerDaoImpl extends IDAOImpl<LogWtProcessReadFinger> implements LogWtProcessReadFingerDao {

	@Override
	public Class<LogWtProcessReadFinger> getEntityClass() {
		// TODO Auto-generated method stub
		return LogWtProcessReadFinger.class;
	}

	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogWtProcessReadFinger temp WHERE temp.wtPeriodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
	}

	@Override
	public LogWtProcessReadFinger getEntityByEmpDataIdAndScheduleDate(Long empDataId, Date scheduleDate) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empDataId", empDataId));
        criteria.add(Restrictions.eq("scheduleDate", scheduleDate));
        return (LogWtProcessReadFinger) criteria.uniqueResult();
	}

	
}
