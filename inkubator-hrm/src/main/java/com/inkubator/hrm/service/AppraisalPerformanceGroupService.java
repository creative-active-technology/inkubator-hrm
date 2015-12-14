package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.web.search.AppraisalPerformanceGroupSearchParameter;

public interface AppraisalPerformanceGroupService extends IService<AppraisalPerformanceGroup>{
	public List<AppraisalPerformanceGroup> getByParam(AppraisalPerformanceGroupSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
	
	public Long getTotalByParam(AppraisalPerformanceGroupSearchParameter searchParameter) throws Exception;
}
