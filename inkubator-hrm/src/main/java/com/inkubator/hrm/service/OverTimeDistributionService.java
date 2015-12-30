/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
import org.hibernate.criterion.Order;
import java.util.List;
/**
 *
 * @author Deni Husni FR
 */
public interface OverTimeDistributionService extends IService<OverTimeDistribution>{
    public List<OverTimeDistribution> getByParamWithDetail(OverTimeDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalOverTimeDistributionByParam(OverTimeDistributionSearchParameter searchParameter) throws Exception;

    public OverTimeDistribution getEntityByParamWithDetail(Long empId, Long overTimeId) throws Exception;

    public List<OverTimeDistribution> getAllDataByIdWithDetail() throws Exception;
    
    public void savePenempatanOt(List<EmpData> data, long id) throws Exception;

}
