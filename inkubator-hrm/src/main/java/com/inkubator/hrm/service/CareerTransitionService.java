package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;

public interface CareerTransitionService extends IService<CareerTransition>{
	public List<CareerTransition> getByParam(CareerTransitionSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(CareerTransitionSearchParameter parameter) throws Exception;

    public CareerTransition getEntityByPKWithDetail(Long id) throws Exception;
}
