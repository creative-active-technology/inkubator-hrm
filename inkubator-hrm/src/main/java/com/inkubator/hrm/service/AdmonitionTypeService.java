package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface AdmonitionTypeService extends IService<AdmonitionType> {

	public List<AdmonitionType> getByParam(AdmonitionTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(AdmonitionTypeSearchParameter parameter) throws Exception;

}
