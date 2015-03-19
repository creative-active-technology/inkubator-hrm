package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.web.search.RmbsTypeSearchParameter;

public interface RmbsTypeDao extends IDAO<RmbsType> {

	public List<RmbsType> getByParam(RmbsTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(RmbsTypeSearchParameter parameter);
	
	public List<RmbsType> getAllDataByStatusActive();

	public RmbsType getEntityByPkWithDetail(Long id);
	
}
