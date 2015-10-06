package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.web.model.ReportPermitHistoryModel;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;
//import com.inkubator.hrm.entity.PermitImplementationDate;
import com.inkubator.hrm.web.search.ReportPermitHistorySearchParameter;

/**
 *
 * @author Taufik
 */
public interface PermitImplementationService extends IService<PermitImplementation>, BaseApprovalService {

    public List<PermitImplementation> getByParam(PermitImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(PermitImplementationSearchParameter parameter) throws Exception;

    public PermitImplementation getEntityByPkWithDetail(Long id) throws Exception;

    public PermitImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) throws Exception;

    public PermitImplementation getLatestEntityByEmpDataId(Long empDataId) throws Exception;

    public Double getTotalActualPermit(Long empDataId, Long permitClassificationId, Date startDate, Date endDate) throws Exception;

    public List<Date> getAllActualPermit(Long empDataId, Long permitClassificationId, Date startDate, Date endDate) throws Exception;

    public String save(PermitImplementation entity, boolean isBypassApprovalChecking) throws Exception;

    public List<ReportPermitHistoryModel> getReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter) throws Exception;    

    public String save(PermitImplementation entity, UploadedFile documentFile) throws Exception;

    public void update(PermitImplementation entity, UploadedFile documentFile) throws Exception;
    
    public String save(PermitImplementation entity, UploadedFile documentFile, boolean isBypassApprovalChecking) throws Exception;

    public List<EmpData> getListApproverByEmpDataId(Long empDataId) throws Exception;
    
    public List<EmpData> getListApproverByEmpDataId(Long empDataId, Long permitDistributionId) throws Exception;
}
