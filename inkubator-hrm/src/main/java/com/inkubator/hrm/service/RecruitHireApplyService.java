package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.RecruitHireApply;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitHireApplyService extends IService<RecruitHireApply> {

    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter) throws Exception;
}
