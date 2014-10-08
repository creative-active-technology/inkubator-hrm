package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.web.search.LeaveSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LeaveDao extends IDAO<Leave> {

	public List<Leave> getByParam(LeaveSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(LeaveSearchParameter parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);
	
	public Long getTotalByCode(String code);
	
	public Long getTotalByCodeAndNotId(String code, Long id);
	
	public Leave getEntityByPkFetchAttendStatus(Long id);

	public Leave getEntityByPkFetchApprovalDefinition(Long id);
	
	public List<Leave> getAllDataByIsActiveAndIsOnlyOncePerEmployee(boolean isActive, boolean isOnlyOncePerEmployee);
	
}
