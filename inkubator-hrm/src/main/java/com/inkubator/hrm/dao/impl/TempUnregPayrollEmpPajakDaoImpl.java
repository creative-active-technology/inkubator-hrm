package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempUnregPayrollEmpPajakDao;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;

/**
 *
 * @author rizkykojek
 */
public class TempUnregPayrollEmpPajakDaoImpl extends IDAOImpl<TempUnregPayrollEmpPajak> implements TempUnregPayrollEmpPajakDao {

	@Override
	public Class<TempUnregPayrollEmpPajak> getEntityClass() {
		return TempUnregPayrollEmpPajak.class;
		
	}
	
}
