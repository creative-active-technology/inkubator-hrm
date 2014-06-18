package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OccupationType;

/**
*
* @author Taufik Hidayat
*/
public interface OccupationTypeDao extends IDAO<OccupationType> {

	public List<OccupationType> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalOccupationTypeByParam(String parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
