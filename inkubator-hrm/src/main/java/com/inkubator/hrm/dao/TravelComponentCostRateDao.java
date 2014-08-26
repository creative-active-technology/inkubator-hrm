/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.web.search.TravelComponentCostRateSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface TravelComponentCostRateDao extends IDAO<TravelComponentCostRate>{
    public List<TravelComponentCostRate> getAllDataWithAllRelation(TravelComponentCostRateSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalTravelComponentRateByParam(TravelComponentCostRateSearchParameter searchParameter);
    
    public TravelComponentCostRate getEntityByPkWithAllRelation(Long id);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public Long getByTravelComponentCostRateCode(String code);

	public List<TravelComponentCostRate> getAllDataByGolJabatanIdAndTravelZoneId(Long golJabatanId, Long travelZoneId);
}
