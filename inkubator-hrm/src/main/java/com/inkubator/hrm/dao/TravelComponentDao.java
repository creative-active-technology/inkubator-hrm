package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TravelComponent;
import com.inkubator.hrm.web.search.TravelComponentSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface TravelComponentDao extends IDAO<TravelComponent> {

	public List<TravelComponent> getByParam(TravelComponentSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalTravelComponentByParam(TravelComponentSearchParameter parameter);

}
