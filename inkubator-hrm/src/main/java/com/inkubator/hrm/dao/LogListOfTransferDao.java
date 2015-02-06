package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogListOfTransfer;

/**
*
* @author rizkykojek
*/
public interface LogListOfTransferDao extends IDAO<LogListOfTransfer> {

	public void deleteByPeriodId(Long periodId);
	
}
