package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.web.search.RmbsSchemaSearchParameter;

public interface RmbsSchemaDao extends IDAO<RmbsSchema> {

	public List<RmbsSchema> getByParam(RmbsSchemaSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(RmbsSchemaSearchParameter parameter);
	
	public List<RmbsSchema> getAllDataByStatusActive();
	
	public Long getTotalByNomorSk(String nomorSk);
	
	public Long getTotalByNomorSkAndNotId(String nomorSk, Long id);

	public RmbsSchema getEntityByPkFetchApprovalDefinition(Long id);	
	
}
