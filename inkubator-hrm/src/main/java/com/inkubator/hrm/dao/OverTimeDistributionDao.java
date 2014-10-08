/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;
import java.util.List;
/**
 *
 * @author Deni Husni FR
 */
public interface OverTimeDistributionDao extends IDAO<OverTimeDistribution>{

    public List<OverTimeDistribution> getByParamWithDetail(OverTimeDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalOverTimeDistributionByParam(OverTimeDistributionSearchParameter searchParameter);

    public OverTimeDistribution getEntityByParamWithDetail(Long id);
    
    public List<OverTimeDistribution> getAllDataByIdWithDetail();
    
    public void saveBatch(List<OverTimeDistribution> data);
}
