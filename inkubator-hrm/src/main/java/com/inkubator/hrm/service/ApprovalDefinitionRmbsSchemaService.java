package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalDefinitionRmbsSchemaService extends IService<ApprovalDefinitionRmbsSchema> {

	public List<ApprovalDefinitionRmbsSchema> getAllDataByRmbsSchemaId(Long rmbsSchemaId) throws Exception;
	
}
