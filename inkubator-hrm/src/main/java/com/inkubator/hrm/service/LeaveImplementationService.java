package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationService extends IService<LeaveImplementation>,BaseApprovalService {
	
	public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LeaveImplementationSearchParameter parameter) throws Exception;

	public LeaveImplementation getEntityByPkWithDetail(Long id) throws Exception;
	
	public LeaveImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception;
	
	public LeaveImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception;

	public Double getTotalActualLeave(Long empDataId, Long leaveId, Date startDate, Date endDate) throws Exception;
	
	public List<Date> getAllActualLeave(Long empDataId, Long leaveId, Date startDate, Date endDate) throws Exception;
	
	public String save(LeaveImplementation entity, boolean isBypassApprovalChecking) throws Exception;
	
}
