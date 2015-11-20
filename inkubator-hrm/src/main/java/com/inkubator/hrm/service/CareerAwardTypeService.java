package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;

public interface CareerAwardTypeService extends IService<CareerAwardType>{
	public List<CareerAwardType> getByParam(CareerAwardTypeSearchParameter searchParameter, int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(CareerAwardTypeSearchParameter searchParameter);
	
	public CareerAwardType getEntityByPkWithDetail(long id);
}
