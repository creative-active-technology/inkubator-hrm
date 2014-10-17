/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.web.search.ImplementationOfOvertimeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ImplementationOfOverTimeService extends IService<ImplementationOfOverTime>, BaseApprovalService{
    public List<ImplementationOfOverTime> getAllDataWithDetail(ImplementationOfOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalImplementationOfOverTimeByParam(ImplementationOfOvertimeSearchParameter searchParameter) throws Exception;
    
    public ImplementationOfOverTime getEntityByPkWithDetail(Long id) throws Exception;
}
