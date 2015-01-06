/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.web.search.UnregPayComponentSearchParameter;

/**
 *
 * @author deni
 */
public interface UnregPayComponentDao extends IDAO<UnregPayComponents> {
	
    public List<UnregPayComponents> getByParam(Long unregSalaryId, UnregPayComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(Long unregSalaryId, UnregPayComponentSearchParameter searchParameter);
    
}
