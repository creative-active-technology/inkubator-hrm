/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.web.search.CareerTerminationTypeSearchParameter;

/**
 *
 * @author Deni
 */
public interface CareerTerminationTypeDao extends IDAO<CareerTerminationType>{
	public List<CareerTerminationType> getListByParam(CareerTerminationTypeSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(CareerTerminationTypeSearchParameter searchParameter);
	
	public CareerTerminationType getEntityWithDetailById(Long id);
    
}
