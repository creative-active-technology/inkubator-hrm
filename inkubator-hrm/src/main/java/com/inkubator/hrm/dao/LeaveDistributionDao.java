/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LeaveDistributionDao extends IDAO<LeaveDistribution>{
    public List<LeaveDistribution> getByParamWithDetail(LeaveDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalLeaveDistributionByParam(LeaveDistributionSearchParameter searchParameter);
    
    public LeaveDistribution getEntityByParamWithDetail(Long empId);
    
    public List<LeaveDistribution> getAllDataByEmpIdWithDetail();
}
