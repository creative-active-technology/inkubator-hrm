package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Religion;

/**
 *
 * @author rizkykojek
 */
public interface ReligionService extends IService<Religion> {

    public List<Religion> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalReligionByParam(String parameter) throws Exception;
    
    public Religion getEntityByUnregSalaryIdWithDetail(Long unregSalaryId) throws Exception;

}
