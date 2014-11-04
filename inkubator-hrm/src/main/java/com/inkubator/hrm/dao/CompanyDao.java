package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.web.search.CompanySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CompanyDao extends IDAO<Company> {

	public List<Company> getByParam(CompanySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(CompanySearchParameter parameter);
	
}
