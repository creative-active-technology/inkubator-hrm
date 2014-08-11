package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TravelZone;
import com.inkubator.hrm.web.search.TravelZoneSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface TravelZoneService extends IService<TravelZone> {

	public List<TravelZone> getByParam(TravelZoneSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(TravelZoneSearchParameter parameter) throws Exception;

}
