package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.web.search.CountrySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface CountryService extends IService<Country> {

	public List<Country> getByParam(CountrySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(CountrySearchParameter parameter) throws Exception;

}
