package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveImplementationDate;

import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationDateDao extends IDAO<LeaveImplementationDate> {
    
	public LeaveImplementationDate getByLeavIdDateAndIsTrue(long leavId, Date doDate, Boolean param);
	
	public Long getTotalActualLeave(Date date, Long companyId);
	
}
