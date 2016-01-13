package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.web.search.CareerDisciplineTypeSearchParameter;

public interface CareerDisciplineTypeService extends IService<CareerDisciplineType>{
	public List<CareerDisciplineType> getByParam(CareerDisciplineTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order);
	
	public Long getTotalDataByParam(CareerDisciplineTypeSearchParameter searchParameter);
	
	public CareerDisciplineType getEntityByIdWithDetail(Long id) throws Exception;
	
}
