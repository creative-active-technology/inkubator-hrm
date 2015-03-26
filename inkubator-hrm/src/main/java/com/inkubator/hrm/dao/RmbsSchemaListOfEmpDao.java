package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsSchemaListOfEmp;
import com.inkubator.hrm.web.model.RmbsSchemaEmpViewModel;
import com.inkubator.hrm.web.search.RmbsSchemaEmpSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsSchemaListOfEmpDao extends IDAO<RmbsSchemaListOfEmp> {

	public List<RmbsSchemaEmpViewModel> getByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalByParamEmployeeSchema(RmbsSchemaEmpSearchParameter parameter);

	public List<RmbsSchemaListOfEmp> getAllDataByEmpDataId(Long empDataId);

	public Long getTotalByNomorSk(String nomorSk);

	public Long getTotalByNomorSkAndNotId(String nomorSk, Long empDataId, Long rmbsSchemaId);
	
	public RmbsSchemaListOfEmp getEntityByPk(Long empDataId, Long rmbsSchemaId);
	
	public RmbsSchemaListOfEmp getEntityByPkWithDetail(Long empDataId, Long rmbsSchemaId);
	
}
