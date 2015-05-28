package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;

/**
*
* @author rizkykojek
*/
public interface WtEmpCorrectionAttendanceService extends IService<WtEmpCorrectionAttendance>, BaseApprovalService {

	public String saveWithApproval(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail) throws Exception;
	
	public String saveWithRevised(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail, Long approvalActivityId) throws Exception;

	public WtEmpCorrectionAttendance getEntityByPkWithDetail(Long id);
}
