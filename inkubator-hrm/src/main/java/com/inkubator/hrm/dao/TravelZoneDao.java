package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.web.search.TravelZoneSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface TravelZoneDao extends IDAO<TravelZone> {

	public List<TravelZone> getByParam(TravelZoneSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalTravelZoneByParam(TravelZoneSearchParameter parameter);

}
