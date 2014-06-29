package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.web.search.CountrySearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface CountryDao extends IDAO<Country> {

	public List<Country> getByParam(CountrySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalCountryByParam(CountrySearchParameter parameter);
	
	public Long getTotalByCode(String name);
        
        public Long getTotalByPhoneCode(Integer phoneCode);
	
	public Long getTotalByCodeAndNotId(String code, Long id);

}
