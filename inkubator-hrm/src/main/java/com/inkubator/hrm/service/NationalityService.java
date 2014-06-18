package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.web.search.NationalitySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface NationalityService extends IService<Nationality> {

	public List<Nationality> getByParam(NationalitySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(NationalitySearchParameter parameter) throws Exception;

}
