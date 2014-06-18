package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.web.search.DialectSearchParameter;

/**
*
* @author Taufik hidayat
*/
public interface DialectDao extends IDAO<Dialect> {

	public List<Dialect> getByParam(DialectSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalDialectByParam(DialectSearchParameter parameter);
	
	public Long getTotalByCode(String name);
	
	public Long getTotalByCodeAndNotId(String code, Long id);

}
