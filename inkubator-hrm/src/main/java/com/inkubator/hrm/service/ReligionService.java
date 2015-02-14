package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.web.search.ReligionSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface ReligionService extends IService<Religion> {

    public List<Religion> getByParam(ReligionSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalReligionByParam(ReligionSearchParameter parameter) throws Exception;
    
    public Religion getEntityByUnregSalaryIdWithDetail(Long unregSalaryId) throws Exception;

}
