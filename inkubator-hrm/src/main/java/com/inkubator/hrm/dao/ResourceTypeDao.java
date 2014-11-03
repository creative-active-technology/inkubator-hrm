/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ResourceType;
import com.inkubator.hrm.web.search.ResourceTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ResourceTypeDao extends IDAO<ResourceType>{
    public List<ResourceType> getByParam(ResourceTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalResourceTypeByParam(ResourceTypeSearchParameter searchParameter);
}
