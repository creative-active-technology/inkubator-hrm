package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.web.search.LeaveImplementationReportSearchParameter;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;

import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public interface LeaveImplementationDao extends IDAO<LeaveImplementation> {

    public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(LeaveImplementationSearchParameter parameter);

    public LeaveImplementation getEntityByPkWithDetail(Long id);

    public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId, Order order);

    public long getTotalByNumberFilling(String numberFilling);

    public long getTotalByNumberFillingAndNotId(String numberFilling, Long id);

    public LeaveImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber);

    public List<LeaveImplementation> getReportByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, int firstResult, int maxResults, Order orderable);

    public Long getReportTotalByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId);

    public List<LeaveImplementation> getReportHistoryByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId);

    public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId);

    public LeaveImplementation getByEmpStardDateEndDate(long empId, Date doDate);
    
    public List<LeaveImplementation> getListByStartDateBetweenDateAndEmpId(Long empDataId,	Date dateFrom, Date dateUntill);

    public Long getCurrentMaxId();
}
