/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.web.search.CareerTerminationTypeSearchParameter;

/**
 *
 * @author Deni
 */
public interface CareerTerminationTypeService extends IService<CareerTerminationType> {
	public List<CareerTerminationType> getListByParam(CareerTerminationTypeSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(CareerTerminationTypeSearchParameter searchParameter) throws Exception;
	
	public CareerTerminationType getEntityWithDetailById(Long id)throws Exception;
}
