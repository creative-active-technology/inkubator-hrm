package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LogUnregTaxesDao;
import com.inkubator.hrm.entity.LogUnregTaxes;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "logUnregTaxesDao")
@Lazy
public class LogUnregTaxesDaoImpl extends IDAOImpl<LogUnregTaxes> implements LogUnregTaxesDao {

	@Override
	public Class<LogUnregTaxes> getEntityClass() {
		return LogUnregTaxes.class;
		
	}

	
}
