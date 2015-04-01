package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewApplication;


/**
 * 
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationDao extends IDAO<LoanNewApplication> {

//	public List<LoanNewApplication> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable);
//
//	public Long getTotalCityByParam(CitySearchParameter parameter);
        
	public Long getTotalByCode(String name);

	public Long getTotalByCodeAndNotId(String code, Long id);

	public LoanNewApplication getEntityByIdWithDetail(Long id);	
	
        public Long getCurrentMaxId();
}
