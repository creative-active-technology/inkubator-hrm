package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalProgramEmp;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.AppraisalProgramEmployeeSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalProgramEmpService extends IService<AppraisalProgramEmp> {

	public List<EmpData> getAllEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception;
	
	public Long getTotalEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter searchParameter) throws Exception;
	
}
