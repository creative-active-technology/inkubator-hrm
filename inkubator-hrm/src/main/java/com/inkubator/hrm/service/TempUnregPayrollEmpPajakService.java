package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.web.model.UnregPayrollEmpPajakModel;
import com.inkubator.hrm.web.search.UnregPayrollEmpPajakSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface TempUnregPayrollEmpPajakService extends IService<TempUnregPayrollEmpPajak> {

	public void deleteByUnregSalaryId(Long unregSalaryId) throws Exception;

	public List<UnregPayrollEmpPajakModel> getAllDataGroupingTaxCompByUnregSalaryId(Long unregSalaryId) throws Exception;

	public List<TempUnregPayrollEmpPajak> getByParam(UnregPayrollEmpPajakSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception;

	public Long getTotalByParam(UnregPayrollEmpPajakSearchParameter parameter) throws Exception;

}
