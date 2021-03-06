package com.inkubator.hrm.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogUnregPayroll;
import com.inkubator.hrm.web.model.UnregPayrollViewModel;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogUnregPayrollService extends IService<LogUnregPayroll> {

	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception;

	public void afterPayrollProcess(Long unregSalaryId) throws Exception;
	
	public Long getTotalEmployeeByUnregSalaryId(Long unregSalaryId);
	
	public BigDecimal getTotalTakeHomePayByUnregSalaryId(Long unregSalaryId);
	
	public List<UnregPayrollViewModel> getByParam(UnregPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(UnregPayrollSearchParameter parameter);
	
	public List<LogUnregPayroll> getAllDataByEmpDataIdAndUnregSalaryId(Long empDataId, Long unregSalaryId) throws Exception;

	public StreamedContent generatePersonalSalarySlip(Long unregSalaryId, Long empDataId) throws Exception;

	public StreamedContent generateMassSalarySlip(UnregPayrollSearchParameter searchParameter) throws Exception;

}
