package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.web.search.CompanySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CompanyService extends IService<Company> {

	public List<Company> getByParam(CompanySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(CompanySearchParameter parameter) throws Exception;
	
	public Company getEntityByPKWithDetail(Long id) throws Exception;
	
}
