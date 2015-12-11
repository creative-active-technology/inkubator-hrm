package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;

public interface AppraisalSystemScoringService extends IService<AppraisalSystemScoring>{
	public List<AppraisalSystemScoring> getByParam(AppraisalSystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(AppraisalSystemScoringSearchParameter searchParameter) throws Exception;
}
