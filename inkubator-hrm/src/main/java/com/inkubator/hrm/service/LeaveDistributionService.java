/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LeaveDistributionService extends IService<LeaveDistribution>{
    public List<LeaveDistribution> getByParamWithDetail(LeaveDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalLeaveDistributionByParam(LeaveDistributionSearchParameter searchParameter) throws Exception;

    public LeaveDistribution getEntityByParamWithDetail(Long empId) throws Exception;
}
