package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmpId;
import com.inkubator.hrm.web.model.RmbsSchemaEmpViewModel;
import com.inkubator.hrm.web.search.RmbsSchemaEmpSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsSchemaListOfEmpService extends IService<RmbsSchemaListOfEmp> {

	public List<RmbsSchemaEmpViewModel> getByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
	
	public Long getTotalByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter) throws Exception;
	
	public RmbsSchemaListOfEmp getEntityByEmpDataId(Long empDataId) throws Exception;

	public RmbsSchemaListOfEmp getEntityByPkWithDetail(Long empDataId, Long rmbsSchemaId);

	public void update(RmbsSchemaListOfEmpId id, RmbsSchemaListOfEmp entity) throws Exception;
	
}
