package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.web.search.LeaveSchemeSearchParameter;

/**
*
* @author rizkykojek
*/
public interface LeaveSchemeService extends IService<LeaveScheme> {

	public List<LeaveScheme> getByParam(LeaveSchemeSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LeaveSchemeSearchParameter parameter) throws Exception;

}
