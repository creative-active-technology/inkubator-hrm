package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.web.search.EmpCorrectionAttendanceSearchParameter;

/**
*
* @author rizkykojek
*/
public interface WtEmpCorrectionAttendanceService extends IService<WtEmpCorrectionAttendance>, BaseApprovalService {

	public String saveWithApproval(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail) throws Exception;
	
	public String saveWithRevised(WtEmpCorrectionAttendance entity, List<WtEmpCorrectionAttendanceDetail> listDetail, Long approvalActivityId) throws Exception;

	public WtEmpCorrectionAttendance getEntityByPkWithDetail(Long id) throws Exception;
	
	public List<WtEmpCorrectionAttendance> getByParam(EmpCorrectionAttendanceSearchParameter parameter, int firstResult, int maxResult, Order orderable) throws Exception;
	
	public Long getTotalByParam(EmpCorrectionAttendanceSearchParameter parameter) throws Exception;

}
