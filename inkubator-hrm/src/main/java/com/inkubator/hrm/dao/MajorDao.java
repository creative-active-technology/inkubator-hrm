package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Major;

/**
*
* @author Taufik Hidayat
*/
public interface MajorDao extends IDAO<Major> {

	public List<Major> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalMajorByParam(String parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
