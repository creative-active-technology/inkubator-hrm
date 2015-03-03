package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempUnregPayrollDao;
import com.inkubator.hrm.entity.TempUnregPayroll;

/**
 *
 * @author rizkykojek
 */
public class TempUnregPayrollDaoImpl extends IDAOImpl<TempUnregPayroll> implements TempUnregPayrollDao {

	@Override
	public Class<TempUnregPayroll> getEntityClass() {
		return TempUnregPayroll.class;
		
	}
	
}
