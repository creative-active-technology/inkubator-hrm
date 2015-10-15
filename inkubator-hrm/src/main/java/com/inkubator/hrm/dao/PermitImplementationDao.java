package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.web.search.ReportPermitHistorySearchParameter;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;

/**
 *
 * @author Taufik
 */
public interface PermitImplementationDao extends IDAO<PermitImplementation> {

    public List<PermitImplementation> getByParam(PermitImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(PermitImplementationSearchParameter parameter);

    public PermitImplementation getEntityByPkWithDetail(Long id);

    public List<PermitImplementation> getAllDataByEmpDataId(Long empDataId, Order order);

    public long getTotalByNumberFilling(String numberFilling);

    public long getTotalByNumberFillingAndNotId(String numberFilling, Long id);

    public PermitImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber);

    public List<PermitImplementation> getReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getReportPermitHistoryTotalByParam(ReportPermitHistorySearchParameter parameter);    
    
    public List<PermitImplementation> getListByStartDateBetweenDateAndEmpId(Long empDataId, Date dateFrom, Date dateUntill);

    public PermitImplementation getByEmpStardDateEndDate(long empId, Date doDate);
    
    public Long getTotalActualPermit(Date date);
    
    public Long getCurrentMaxId();
    
    public Long getTotalEmployeeByEmployeeId(Long id);

}
