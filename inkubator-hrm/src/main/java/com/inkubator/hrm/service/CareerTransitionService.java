package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CarreerTransition;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;

public interface CareerTransitionService extends IService<CarreerTransition>{
	public List<CarreerTransition> getByParam(CareerTransitionSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(CareerTransitionSearchParameter parameter) throws Exception;

    public CarreerTransition getEntityByPKWithDetail(Long id) throws Exception;
}
