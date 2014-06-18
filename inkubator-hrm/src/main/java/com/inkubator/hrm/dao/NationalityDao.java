package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.web.search.NationalitySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface NationalityDao extends IDAO<Nationality> {

	public List<Nationality> getByParam(NationalitySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalNationalityByParam(NationalitySearchParameter parameter);
	
	public Long getTotalByCode(String name);
	
	public Long getTotalByCodeAndNotId(String code, Long id);

}
