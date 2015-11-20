package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.web.search.CareerDisciplineTypeSearchParameter;

public interface CareerDisciplineTypeDao extends IDAO<CareerDisciplineType>{
	public List<CareerDisciplineType> getByParam(CareerDisciplineTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order);
	
	public Long getTotalDataByParam(CareerDisciplineTypeSearchParameter searchParameter);
	
	public CareerDisciplineType getEntityWithDetail(long id);
}
