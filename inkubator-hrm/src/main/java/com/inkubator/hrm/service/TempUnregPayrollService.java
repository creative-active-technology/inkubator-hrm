package com.inkubator.hrm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;

/**
 *
 * @author rizkykojek
 */
public interface TempUnregPayrollService extends IService<TempUnregPayroll> {

	public List<TempUnregPayroll> getAllDataCalculatedPayment(Long unregSalaryId, Date createdOn, String createdBy) throws Exception;

	public List<TempUnregPayroll> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) throws Exception;

	public void executeBatchUnregCalculationFinal(HashMap<String, Long> map) throws ScriptException;

	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception;

	public List<UnregSalaryCalculationExecuteModel> getByParamUnregSalaryId(Long unregSalaryId, int first, int pageSize, Order orderable);

	public Long getTotalByParamUnregSalaryId(Long unregSalaryId);
	
}
