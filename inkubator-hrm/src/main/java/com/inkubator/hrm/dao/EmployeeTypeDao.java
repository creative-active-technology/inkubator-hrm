package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmployeeType;

/**
*
* @author rizkykojek
*/
public interface EmployeeTypeDao extends IDAO<EmployeeType> {

	public List<EmployeeType> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(String parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
