package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.web.search.ReligionSearchParameter;

/**
*
* @author rizkykojek
*/
public interface ReligionDao extends IDAO<Religion> {

	public List<Religion> getByParam(ReligionSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalReligionByParam(ReligionSearchParameter parameter);
	
	public Long getTotalByName(String name);
	
	public Long getTotalByNameAndNotId(String name, Long id);

}
