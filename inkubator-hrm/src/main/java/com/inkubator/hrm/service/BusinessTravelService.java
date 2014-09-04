package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface BusinessTravelService extends IService<BusinessTravel> {

	public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(BusinessTravelSearchParameter parameter) throws Exception;
	
	public void save(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents, boolean isBypassApprovalChecking) throws Exception;
	
	public void update(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents) throws Exception;
	
	public BusinessTravel getEntityByPkWithDetail(Long id) throws Exception;
	
	public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo) throws Exception;
}
