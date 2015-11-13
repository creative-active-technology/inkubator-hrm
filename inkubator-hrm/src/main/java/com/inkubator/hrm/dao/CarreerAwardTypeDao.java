package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CarreerAwardType;
import com.inkubator.hrm.web.search.CarreerAwardTypeSearchParameter;

public interface CarreerAwardTypeDao extends IDAO<CarreerAwardType>{
	public List<CarreerAwardType> getByParam(CarreerAwardTypeSearchParameter searchParameter, int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(CarreerAwardTypeSearchParameter searchParameter);
	
	public CarreerAwardType getEntityByPkWithDetail(long id);
}
