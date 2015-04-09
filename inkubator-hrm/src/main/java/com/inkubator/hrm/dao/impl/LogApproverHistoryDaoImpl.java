package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogApproverHistoryDao;
import com.inkubator.hrm.entity.LogApproverHistory;

@Repository(value = "logApproverHistoryDao")
@Lazy
public class LogApproverHistoryDaoImpl extends IDAOImpl<LogApproverHistory> implements LogApproverHistoryDao {

	@Override
	public Class<LogApproverHistory> getEntityClass() {
		// TODO Auto-generated method stub
		return LogApproverHistory.class;
	}

	
}
