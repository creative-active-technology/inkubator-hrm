package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.web.search.AccessHistorySearchParameter;

public interface AccessHistoryDao extends IDAO<RiwayatAkses>{
	
	public List<RiwayatAkses> getByParam(AccessHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order);
	
	public Long getTotalByParam(AccessHistorySearchParameter searchParameter);
}
