package com.inkubator.hrm.dao.impl;

import org.hibernate.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogUnregListOfTransferDao;
import com.inkubator.hrm.entity.LogUnregListOfTransfer;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logUnregListOfTransferDao")
@Lazy
public class LogUnregListOfTransferDaoImpl extends IDAOImpl<LogUnregListOfTransfer> implements LogUnregListOfTransferDao {

	@Override
	public Class<LogUnregListOfTransfer> getEntityClass() {
		return LogUnregListOfTransfer.class;
		
	}

	@Override
	public void deleteByUnregSalaryId(Long unregSalaryId) {
		Query query = getCurrentSession().createQuery("delete from LogUnregListOfTransfer where unregSalaryId = :unregSalaryId")
				.setLong("unregSalaryId", unregSalaryId);
        query.executeUpdate();
	}

	
}
