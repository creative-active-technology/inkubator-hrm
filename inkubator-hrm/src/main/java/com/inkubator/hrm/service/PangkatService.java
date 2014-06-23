package com.inkubator.hrm.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.web.search.PangkatSearchParameter;

/**
*
* @author rizkykojek
*/
public interface PangkatService extends IService<Pangkat> {

	public List<Pangkat> getByParam(PangkatSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(PangkatSearchParameter parameter) throws Exception;
	
	public Map<Long, String> getAllDataMaps();
}
