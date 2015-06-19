package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogWtProcessReadFinger;

/**
*
* @author rizkykojek
*/
public interface LogWtProcessReadFingerService extends IService<LogWtProcessReadFinger> {

	public void deleteByPeriodId(Long periodId);

}
