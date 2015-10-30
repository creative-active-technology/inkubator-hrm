package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.web.search.AccessHistorySearchParameter;

public interface AccessHistoryService extends IService<RiwayatAkses>{
	
	public List<RiwayatAkses> getByParam(AccessHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
	
	public Long getTotalByParam(AccessHistorySearchParameter searchParameter) throws Exception;
	
}
