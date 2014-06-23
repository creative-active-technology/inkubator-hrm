/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.web.search.CostCenterSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface CostCenterService extends IService<CostCenter> {
    public List<CostCenter> getByParam(CostCenterSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalCostCenterByParam(CostCenterSearchParameter searchParameter);

    public Long getByCostCenterName(String name);
    
    public CostCenter getCostCenterByIdWithDetail(Long id) throws Exception;
}
