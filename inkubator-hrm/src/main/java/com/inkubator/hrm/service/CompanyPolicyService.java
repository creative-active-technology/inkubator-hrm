package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.web.search.CompanyPolicySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CompanyPolicyService extends IService<CompanyPolicy> {
	
	public List<CompanyPolicy> getByParam(CompanyPolicySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(CompanyPolicySearchParameter parameter) throws Exception;
        
    public CompanyPolicy getEntityByPkWithDetail(Long id) throws Exception;
}
