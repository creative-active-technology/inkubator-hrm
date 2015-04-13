package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsCancelation;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.RmbsCancelationSearchParameter;

/**
*
* @author rizkykojek
*/
public interface RmbsCancelationDao extends IDAO<RmbsCancelation> {

	public Long getCurrentMaxId();
	
	public List<RmbsCancelationViewModel> getByParam(RmbsCancelationSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(RmbsCancelationSearchParameter parameter);

}
