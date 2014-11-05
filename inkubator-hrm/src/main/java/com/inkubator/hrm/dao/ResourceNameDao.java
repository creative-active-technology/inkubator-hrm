/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ResourceName;
import com.inkubator.hrm.web.search.ResourceNameSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ResourceNameDao extends IDAO<ResourceName>{
    public List<ResourceName> getByParam(ResourceNameSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalResourceNameByParam(ResourceNameSearchParameter searchParameter);
}
