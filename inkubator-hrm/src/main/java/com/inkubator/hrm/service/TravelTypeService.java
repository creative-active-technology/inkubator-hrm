package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TravelType;
import com.inkubator.hrm.web.search.TravelTypeSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface TravelTypeService extends IService<TravelType> {

	public List<TravelType> getByParam(TravelTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(TravelTypeSearchParameter parameter) throws Exception;
        
        public TravelType getEntityByPKWithDetail(Long id) throws Exception;

}
