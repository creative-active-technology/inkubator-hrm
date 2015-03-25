package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;

/**
 *
 * @author rizkykojek
 */
public interface RmbsSchemaListOfTypeService extends IService<RmbsSchemaListOfType> {

	public List<RmbsSchemaListOfType> getAllDataByRmbsSchemaId(Long rmbsSchemaId) throws Exception;
	
	public RmbsSchemaListOfType getEntityByPk(RmbsSchemaListOfTypeId id) throws Exception;
	
}
