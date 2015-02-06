package com.inkubator.hrm.dao.impl;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogListOfTransferDao;
import com.inkubator.hrm.entity.LogListOfTransfer;

/**
*
* @author rizkykojek
*/
@Repository(value = "logListOfTransferDao")
@Lazy
public class LogListOfTransferDaoImpl extends IDAOImpl<LogListOfTransfer> implements LogListOfTransferDao {

	@Override
	public Class<LogListOfTransfer> getEntityClass() {		
		return LogListOfTransfer.class;
	}
	
	@Override
	public void deleteByPeriodId(Long periodId) {
		Query query = getCurrentSession().createQuery("DELETE FROM LogListOfTransfer temp WHERE temp.periodeId = :periodId")
				.setLong("periodId", periodId);
        query.executeUpdate();
		
	}

}
