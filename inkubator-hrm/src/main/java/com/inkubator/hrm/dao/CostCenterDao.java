/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.web.search.CostCenterSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface CostCenterDao extends IDAO<CostCenter>{
    public List<CostCenter> getByParam(CostCenterSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalCostCenterByParam(CostCenterSearchParameter searchParameter);

    public Long getByCostCenterCode(String code);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public CostCenter getCostCenterByIdWithDetail(Long id);
    
    public List<CostCenter> getAllDataWhichIsNotItself(Long id);
}
