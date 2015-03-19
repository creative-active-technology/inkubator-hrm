package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.web.search.RmbsSchemaSearchParameter;

public interface RmbsSchemaService extends IService<RmbsSchema> {

	public List<RmbsSchema> getByParam(RmbsSchemaSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(RmbsSchemaSearchParameter parameter) throws Exception;
	
	public List<RmbsSchema> getAllDataByStatusActive() throws Exception;
	
}
