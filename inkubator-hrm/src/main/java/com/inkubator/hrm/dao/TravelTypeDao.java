package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.web.search.TravelTypeSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface TravelTypeDao extends IDAO<TravelType> {

	public List<TravelType> getByParam(TravelTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalTravelTypeByParam(TravelTypeSearchParameter parameter);

}
