package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationDateDao extends IDAO<LeaveImplementationDate> {
    
	public LeaveImplementationDate getByLeavIdDateAndIsTrue(long leavId, Date doDate, Boolean param);
	
	public Long getTotalActualLeave(Date date, Long companyId);
	
	public List<LeaveImplementationDateModel> getAllDataWithTotalTakenLeaveByEmpDataId(Long empDataId);
	
	public List<LeaveImplementationDate> getAllDataByEmpDataId(Long empDataId);

}
