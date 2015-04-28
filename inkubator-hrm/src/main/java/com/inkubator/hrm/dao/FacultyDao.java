package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.web.search.FacultySearchParameter;


/**
*
* @author Taufik hidayat
*/
public interface FacultyDao extends IDAO<Faculty> {

	public List<Faculty> getByParam(FacultySearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalFacultyByParam(FacultySearchParameter searchParameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
