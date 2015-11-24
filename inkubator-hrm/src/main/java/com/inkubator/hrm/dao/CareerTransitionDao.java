package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;

public interface CareerTransitionDao extends IDAO<CareerTransition>{
	public List<CareerTransition> getByParam(CareerTransitionSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(CareerTransitionSearchParameter parameter);

    public CareerTransition getEntityByPKWithDetail(Long id);
}
