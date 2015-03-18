package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.web.search.RmbsTypeSearchParameter;

public interface RmbsTypeService extends IService<RmbsType> {

	public List<RmbsType> getByParam(RmbsTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(RmbsTypeSearchParameter parameter) throws Exception;
	
	public List<RmbsType> getAllDataByStatusActive() throws Exception;

	public RmbsType getEntityByPkWithDetail(Long id) throws Exception;;
}
