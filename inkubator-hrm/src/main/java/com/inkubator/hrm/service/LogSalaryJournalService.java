package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogSalaryJournal;

/**
*
* @author rizkykojek
*/
public interface LogSalaryJournalService extends IService<LogSalaryJournal> {

	public void deleteByPeriodId(Long periodeId) throws Exception;
	
}
