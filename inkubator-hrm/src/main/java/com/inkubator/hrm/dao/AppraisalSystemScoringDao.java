package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;

public interface AppraisalSystemScoringDao extends IDAO<AppraisalSystemScoring>{
    public List<AppraisalSystemScoring> getByParam(AppraisalSystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(AppraisalSystemScoringSearchParameter searchParameter);
}
