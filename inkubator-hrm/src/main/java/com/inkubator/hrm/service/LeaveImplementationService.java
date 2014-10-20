package com.inkubator.hrm.service;

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
	
	public LeaveImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception;

}
