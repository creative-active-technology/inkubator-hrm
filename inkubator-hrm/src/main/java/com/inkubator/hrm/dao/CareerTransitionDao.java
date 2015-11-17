package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CarreerTransition;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;

public interface CareerTransitionDao extends IDAO<CarreerTransition>{
	public List<CarreerTransition> getByParam(CareerTransitionSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(CareerTransitionSearchParameter parameter);

    public CarreerTransition getEntityByPKWithDetail(Long id);
}
