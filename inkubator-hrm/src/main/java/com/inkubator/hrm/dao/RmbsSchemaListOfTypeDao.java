package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsSchemaListOfType;
import com.inkubator.hrm.entity.RmbsSchemaListOfTypeId;

/**
 *
 * @author rizkykojek
 */
public interface RmbsSchemaListOfTypeDao extends IDAO<RmbsSchemaListOfType> {
	
	public List<RmbsSchemaListOfType> getAllDataByRmbsSchemaId(Long rmbsSchemaId);

	public RmbsSchemaListOfType getEntityByPk(RmbsSchemaListOfTypeId id);
	
}
