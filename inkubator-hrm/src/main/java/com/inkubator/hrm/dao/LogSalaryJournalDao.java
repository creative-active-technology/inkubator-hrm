package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogSalaryJournal;

/**
*
* @author rizkykojek
*/
public interface LogSalaryJournalDao extends IDAO<LogSalaryJournal> {

	public void deleteByPeriodId(Long periodeId);
	
}
