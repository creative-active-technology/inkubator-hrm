package com.inkubator.hrm.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogMonthEndPayrollService extends IService<LogMonthEndPayroll> {
	
	public List<LogMonthEndPayrollViewModel> getByParam(LogMonthEndPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LogMonthEndPayrollSearchParameter parameter) throws Exception;

	public BigDecimal getTotalTakeHomePayByPeriodeId(Long periodeId) throws Exception;
	
	public void afterMonthEndProcess() throws Exception;
	
	public void deleteByPeriodId(Long periodId) throws Exception;

}
