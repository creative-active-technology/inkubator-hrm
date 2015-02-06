package com.inkubator.hrm.service;

import java.util.Date;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogListOfTransfer;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;

/**
*
* @author rizkykojek
*/
public interface LogListOfTransferService extends IService<LogListOfTransfer> {

	public void deleteByPeriodId(Long periodId) throws Exception;
	
	public void executeBatchMonthEndPayroll(PayTempKalkulasi payTempKalkulasi, WtPeriode periode, String createdBy, Date createdOn)  throws Exception;
	
}
