package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.web.search.LeaveImplementationReportSearchParameter;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface PermitImplementationDao extends IDAO<LeaveImplementation> {

	public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(LeaveImplementationSearchParameter parameter);
	
	public LeaveImplementation getEntityByPkWithDetail(Long id);
	
	public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId, Order order);

	public long getTotalByNumberFilling(String numberFilling);

	public long getTotalByNumberFillingAndNotId(String numberFilling, Long id);

	public LeaveImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber);
        
        public List<LeaveImplementation> getReportByParam(LeaveImplementationReportSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getReportTotalByParam(LeaveImplementationReportSearchParameter parameter);
	
}
