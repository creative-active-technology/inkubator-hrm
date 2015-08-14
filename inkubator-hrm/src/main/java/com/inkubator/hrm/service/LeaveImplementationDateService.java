package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.web.model.LeaveImplementationDateModel;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationDateService extends IService<LeaveImplementationDate> {
	public List<LeaveImplementationDateModel> getAllDataWithTotalTakenLeaveByEmpDataId(Long empDataId) throws Exception;
	
	public List<LeaveImplementationDate> getAllDataByEmpDataId(Long empDataId);
}
