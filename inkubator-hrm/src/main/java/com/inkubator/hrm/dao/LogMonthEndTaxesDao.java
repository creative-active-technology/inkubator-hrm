package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogMonthEndTaxes;

/**
*
* @author rizkykojek
*/
public interface LogMonthEndTaxesDao extends IDAO<LogMonthEndTaxes> {

	public void deleteByPeriodId(Long periodId);
	
}
