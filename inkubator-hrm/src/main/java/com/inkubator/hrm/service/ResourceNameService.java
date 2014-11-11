/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ResourceName;
import com.inkubator.hrm.web.search.ResourceNameSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ResourceNameService extends IService<ResourceName>{
    public List<ResourceName> getByParam(ResourceNameSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalResourceTypeByParam(ResourceNameSearchParameter searchParameter) throws Exception;
    
    public ResourceName getEntityByPkWithDetail(Long id) throws Exception;
    
}
