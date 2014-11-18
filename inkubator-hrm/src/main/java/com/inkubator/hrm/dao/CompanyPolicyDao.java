package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.web.search.CompanyPolicySearchParameter;

/**
*
* @author rizkykojek
*/
public interface CompanyPolicyDao extends IDAO<CompanyPolicy> {

	public List<CompanyPolicy> getByParam(CompanyPolicySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(CompanyPolicySearchParameter parameter);
	
	public CompanyPolicy getEntityByPkWithDetail(Long id);
	
	public CompanyPolicy updateAndMerge(CompanyPolicy companyPolicy);

}
