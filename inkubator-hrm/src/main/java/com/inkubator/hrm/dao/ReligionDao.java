package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Religion;

/**
*
* @author rizkykojek
*/
public interface ReligionDao extends IDAO<Religion> {

	public List<Religion> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalReligionByParam(String parameter);

}
