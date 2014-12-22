package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogMonthEndPayrollService extends IService<LogMonthEndPayroll> {
	
	public List<LogMonthEndPayroll> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) throws Exception;

}
