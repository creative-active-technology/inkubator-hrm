package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsCancelation;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.RmbsCancelationSearchParameter;

/**
*
* @author rizkykojek
*/
public interface RmbsCancelationService extends IService<RmbsCancelation> {

	public List<RmbsCancelationViewModel> getByParam(RmbsCancelationSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(RmbsCancelationSearchParameter parameter) throws Exception;
	
}
