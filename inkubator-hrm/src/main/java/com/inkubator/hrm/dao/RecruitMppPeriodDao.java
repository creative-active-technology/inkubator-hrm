package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.RecruitMppPeriodSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitMppPeriodDao extends IDAO<RecruitMppPeriod> {

    public List<RecruitMppPeriod> getByParam(RecruitMppPeriodSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalRecruitMppPeriodByParam(RecruitMppPeriodSearchParameter searchParameter);
}
