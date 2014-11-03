/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ResourceType;
import com.inkubator.hrm.web.search.ResourceTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ResourceTypeService extends IService<ResourceType>{
    public List<ResourceType> getByParam(ResourceTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalResourceTypeByParam(ResourceTypeSearchParameter searchParameter) throws Exception;
}
