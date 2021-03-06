/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.web.model.OvertimeImplSearchingModel;
import com.inkubator.hrm.web.search.ImplementationOfOvertimeSearchParameter;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ImplementationOfOverTimeDao extends IDAO<ImplementationOfOverTime>{
    public List<ImplementationOfOverTime> getAllDataWithDetail(ImplementationOfOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalImplementationOfOvertimeByParam(ImplementationOfOvertimeSearchParameter searchParameter);
    
    public ImplementationOfOverTime getEntityByPkWithDetail(Long id);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public Long getByCode(String code);
    
    public ImplementationOfOverTime getEntityByApprovalActivityNumberWithDetail(String activityNumber);
    
    public List<ImplementationOfOverTime> getAllEmpOtImplBetweenStartDateAndEndDate(Long empDataId, Date startDate, Date endDate);
    
    public Long getCurrentMaxId();
    
    public List<ImplementationOfOverTime> getListSearchByParam(OvertimeImplSearchingModel overtimeImplSearchingModel, int firstResult, int maxResults, Order order);
    
    public Long getTotalListSearchByParam(OvertimeImplSearchingModel overtimeImplSearchingModel);
}
