package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogUnregPayrollDao;
import com.inkubator.hrm.entity.LogUnregPayroll;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logUnregPayrollDao")
@Lazy
public class LogUnregPayrollDaoImpl extends IDAOImpl<LogUnregPayroll> implements LogUnregPayrollDao {

	@Override
	public Class<LogUnregPayroll> getEntityClass() {
		return LogUnregPayroll.class;
		
	}

	

}
