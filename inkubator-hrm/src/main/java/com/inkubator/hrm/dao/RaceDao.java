package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.web.search.RaceSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface RaceDao extends IDAO<Race> {

	public List<Race> getByParam(RaceSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalRaceByParam(RaceSearchParameter parameter);
	
	public Long getTotalByCode(String name);
	
	public Long getTotalByCodeAndNotId(String code, Long id);

}
