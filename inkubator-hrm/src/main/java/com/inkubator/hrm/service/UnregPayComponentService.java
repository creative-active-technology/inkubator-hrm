/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.web.search.UnregPayComponentSearchParameter;

/**
 *
 * @author deni
 */
public interface UnregPayComponentService extends IService<UnregPayComponents> {

    public List<UnregPayComponents> getByParam(Long unregSalaryId, UnregPayComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(Long unregSalaryId, UnregPayComponentSearchParameter searchParameter) throws Exception;
    
}
