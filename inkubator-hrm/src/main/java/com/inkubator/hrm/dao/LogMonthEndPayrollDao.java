package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogMonthEndPayrollDao extends IDAO<LogMonthEndPayroll> {
	
	public List<LogMonthEndPayroll> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter);

}
