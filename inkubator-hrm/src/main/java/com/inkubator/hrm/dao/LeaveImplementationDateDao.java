package com.inkubator.hrm.dao;

import java.util.Date;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveImplementationDate;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationDateDao extends IDAO<LeaveImplementationDate> {

    public LeaveImplementationDate getByLeavIdDateAndIsTrue(long leavId, Date doDate, Boolean param);
	
	public Long getTotalActualLeave(Date date);
	
}
