package com.inkubator.hrm.dao;

import java.util.Date;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogWtProcessReadFinger;

/**
*
* @author rizkykojek
*/
public interface LogWtProcessReadFingerDao extends IDAO<LogWtProcessReadFinger> {

	public void deleteByPeriodId(Long periodId);

	public LogWtProcessReadFinger getEntityByEmpDataIdAndScheduleDate(Long empDataId, Date scheduleDate);

}
