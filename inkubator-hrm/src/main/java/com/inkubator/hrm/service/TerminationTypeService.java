package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TerminationType;
import com.inkubator.hrm.web.search.TerminationTypeSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface TerminationTypeService extends IService<TerminationType> {

	public List<TerminationType> getByParam(TerminationTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(TerminationTypeSearchParameter parameter) throws Exception;

}
