package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.web.search.CitySearchParameter;

/**
 * 
 * @author Taufik Hidayat
 */
public interface CityService extends IService<City> {

	public List<City> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(CitySearchParameter parameter) throws Exception;

	public City getCityByIdWithDetail(Long id) throws Exception;
	
	public List<City> getByProvinceId(Long provinceId) throws Exception;
        
        public List<City> getByProvinceIdWithDetail(Long id) throws Exception;

}
