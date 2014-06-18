package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Faculty;

/**
*
* @author Taufik hidayat
*/
public interface FacultyDao extends IDAO<Faculty> {

	public List<Faculty> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalFacultyByParam(String parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
