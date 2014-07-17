/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.InterestType;
import com.inkubator.hrm.web.search.InterestTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface InterestTypeService extends IService<InterestType>{
    public List<InterestType> getByParam(InterestTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalInterestTypeByParam(InterestTypeSearchParameter searchParameter) throws Exception;

    public Long getByInterestTypeName(String name) throws Exception;
}
