/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OhsaCategory;
import com.inkubator.hrm.web.search.OhsaCategorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface OhsaCategoryService extends IService<OhsaCategory>{
    public List<OhsaCategory> getByParam(OhsaCategorySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalOhsaCategoryByParam(OhsaCategorySearchParameter searchParameter) throws Exception;
}
