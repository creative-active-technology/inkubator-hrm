package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TravelComponent;
import com.inkubator.hrm.web.search.TravelComponentSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface TravelComponentService extends IService<TravelComponent> {

	public List<TravelComponent> getByParam(TravelComponentSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(TravelComponentSearchParameter parameter) throws Exception;

}
