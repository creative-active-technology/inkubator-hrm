package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.RecruitMppPeriod;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.RecruitMppPeriodSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitMppPeriodService extends IService<RecruitMppPeriod> {

    public List<RecruitMppPeriod> getByParam(RecruitMppPeriodSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalRecruitMppPeriodByParam(RecruitMppPeriodSearchParameter searchParameter) throws Exception;
}
