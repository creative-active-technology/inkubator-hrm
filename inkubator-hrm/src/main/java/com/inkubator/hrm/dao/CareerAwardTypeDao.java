package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;

public interface CareerAwardTypeDao extends IDAO<CareerAwardType>{
	public List<CareerAwardType> getByParam(CareerAwardTypeSearchParameter searchParameter, int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(CareerAwardTypeSearchParameter searchParameter);
	
	public CareerAwardType getEntityByPkWithDetail(long id);
}
