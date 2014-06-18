package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.web.search.RaceSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface RaceService extends IService<Race> {

	public List<Race> getByParam(RaceSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(RaceSearchParameter parameter) throws Exception;

}
