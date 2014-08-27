/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Termination;
import com.inkubator.hrm.web.search.TerminationSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface TerminationService extends IService<Termination>{
    public List<Termination> getAllDataWithAllRelation(TerminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalTravelComponentRateByParam(TerminationSearchParameter searchParameter) throws Exception;
    
    public Termination getEntityByPkWithAllRelation(Long code) throws Exception;
}
