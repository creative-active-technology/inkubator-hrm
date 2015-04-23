package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitHireApplyDao extends IDAO<RecruitHireApply> {

    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter);
}
