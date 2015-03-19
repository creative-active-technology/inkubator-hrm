package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinitionRmbsSchema;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalDefinitionRmbsSchemaDao extends IDAO<ApprovalDefinitionRmbsSchema> {
    
	public List<ApprovalDefinitionRmbsSchema> getAllDataByRmbsSchemaId(Long rmbsSchemaId);
    
    public ApprovalDefinitionRmbsSchema getEntityByPk(Long appDefId, Long rmbsSchemaId);
    
}
