package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.web.model.BusinessTravelViewModel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface BusinessTravelService extends IService<BusinessTravel>,BaseApprovalService {

	//functions
	public String saveWithApproval(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents) throws Exception;
	
	public String saveWithRevised(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents, Long approvalActivityId) throws Exception;
	
	public void update(BusinessTravel businessTravel, List<BusinessTravelComponent> businessTravelComponents) throws Exception;
	
	
	//return entity/property
	public BusinessTravel getEntityByPkWithDetail(Long id) throws Exception;
	
	public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo) throws Exception;

	public BusinessTravel getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) throws Exception;
	
	
	//return collections
	public List<BusinessTravel> getAllDataByEmpDataId(Long empDataId) throws Exception;		
	
	
	//pageable
	public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(BusinessTravelSearchParameter parameter) throws Exception;        

	public List<BusinessTravelViewModel> getAllActivityByParam(BusinessTravelSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception;

	public Long getTotalActivityByParam(BusinessTravelSearchParameter parameter) throws Exception;
}
