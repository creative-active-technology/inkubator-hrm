package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogMonthEndTaxes;

/**
*
* @author rizkykojek
*/
public interface LogMonthEndTaxesService extends IService<LogMonthEndTaxes> {

	public void deleteByPeriodId(Long periodId) throws Exception;
	
}
