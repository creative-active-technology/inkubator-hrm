package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.PermitImplementation;
//import com.inkubator.hrm.entity.PermitImplementationDate;
import com.inkubator.hrm.web.search.PermitImplementationReportSearchParameter;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;

/**
 *
 * @author Taufik
 */
public interface PermitImplementationService extends IService<PermitImplementation>,BaseApprovalService {
	
	public List<PermitImplementation> getByParam(PermitImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(PermitImplementationSearchParameter parameter) throws Exception;

	public PermitImplementation getEntityByPkWithDetail(Long id) throws Exception;
	
	public PermitImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception;
	
	public PermitImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception;

	public Double getTotalActualPermit(Long empDataId, Long permitClassificationId, Date startDate, Date endDate) throws Exception;
	
	public List<Date> getAllActualPermit(Long empDataId, Long permitClassificationId, Date startDate, Date endDate) throws Exception;
	
	public String save(PermitImplementation entity, boolean isBypassApprovalChecking) throws Exception;
	
//	public String cancellation(Long permitImplementationId, List<PermitImplementationDate> actualPermits, List<PermitImplementationDate> cancellationPermits, String cancellationDescription) throws Exception;
        
        public List<PermitImplementation> getReportByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getReportTotalByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId ) throws Exception;
	
        public List<PermitImplementation> getReportHistoryByParam(PermitImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId) throws Exception;
}
