package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CarreerAwardType;
import com.inkubator.hrm.web.search.CarreerAwardTypeSearchParameter;

public interface CarreerAwardTypeService extends IService<CarreerAwardType>{
	public List<CarreerAwardType> getByParam(CarreerAwardTypeSearchParameter searchParameter, int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(CarreerAwardTypeSearchParameter searchParameter);
	
	public CarreerAwardType getEntityByPkWithDetail(long id);
}
