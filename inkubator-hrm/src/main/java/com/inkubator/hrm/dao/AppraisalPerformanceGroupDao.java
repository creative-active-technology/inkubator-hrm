package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.web.search.AppraisalPerformanceGroupSearchParameter;
import org.hibernate.criterion.Order;

public interface AppraisalPerformanceGroupDao extends IDAO<AppraisalPerformanceGroup>{
	public List<AppraisalPerformanceGroup> getByParam(AppraisalPerformanceGroupSearchParameter searchParameter, int firstResult, int maxResults, Order order);
	
	public Long getTotalByParam(AppraisalPerformanceGroupSearchParameter searchParameter);
}