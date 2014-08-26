/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.web.search.TravelComponentCostRateSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface TravelComponentCostRateService extends IService<TravelComponentCostRate>{
    public List<TravelComponentCostRate> getAllDataWithAllRelation(TravelComponentCostRateSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalTravelComponentRateByParam(TravelComponentCostRateSearchParameter searchParameter) throws Exception;
    
    public TravelComponentCostRate getEntityByPkWithAllRelation(Long code) throws Exception;
    
    public List<TravelComponentCostRate> getAllDataByEmpDataIdAndTravelZoneId(Long golJabatanId, Long travelZoneId) throws Exception;
}
