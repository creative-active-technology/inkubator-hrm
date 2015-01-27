package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.web.search.EducationNonFormalSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface EducationNonFormalService extends IService<EducationNonFormal> {

	public List<EducationNonFormal> getByParam(EducationNonFormalSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(EducationNonFormalSearchParameter parameter) throws Exception;
        
    public EducationNonFormal getEntityByPkWithDetail(Long id) throws Exception;
    
}
