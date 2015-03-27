package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;


/**
 * 
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationInstallmentDao extends IDAO<LoanNewApplicationInstallment> {

//	public List<LoanNewApplication> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable);
//
//	public Long getTotalCityByParam(CitySearchParameter parameter);

	public Long getTotalByCode(String name);

	public Long getTotalByCodeAndNotId(String code, Long id);

	public LoanNewApplicationInstallment getEntityByIdWithDetail(Long id);	
	

}
