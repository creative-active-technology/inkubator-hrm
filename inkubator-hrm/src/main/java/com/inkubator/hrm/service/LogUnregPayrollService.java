package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogUnregPayroll;

/**
 *
 * @author rizkykojek
 */
public interface LogUnregPayrollService extends IService<LogUnregPayroll> {

	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception;

	public void afterPayrollProcess(Long unregSalaryId) throws Exception;

}
