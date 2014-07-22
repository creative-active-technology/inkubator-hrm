package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.web.search.CitySearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface CityDao extends IDAO<City> {

	public List<City> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalCityByParam(CitySearchParameter parameter);
	
	public Long getTotalByCode(String name);
	
	public Long getTotalByCodeAndNotId(String code, Long id);
        
        public City getCityByIdWithDetail(Long id);

}
