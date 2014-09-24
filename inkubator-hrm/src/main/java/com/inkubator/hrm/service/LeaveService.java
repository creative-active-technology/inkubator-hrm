package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.web.search.LeaveSearchParameter;

/**
*
* @author rizkykojek
*/
public interface LeaveService extends IService<Leave> {

	public List<Leave> getByParam(LeaveSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LeaveSearchParameter parameter) throws Exception;

	public Leave getEntityByPkFetchAttendStatus(Long id) throws Exception;
	
	public Leave getEntityByPkFetchApprovalDefinition(Long id) throws Exception;
	
	public void save(Leave entity, List<ApprovalDefinition> appDefs) throws Exception;
	
	public void update(Leave entity, List<ApprovalDefinition> appDefs) throws Exception;
}
