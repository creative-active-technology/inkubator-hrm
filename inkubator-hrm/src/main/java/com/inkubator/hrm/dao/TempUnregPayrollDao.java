package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;

/**
 *
 * @author rizkykojek
 */
public interface TempUnregPayrollDao extends IDAO<TempUnregPayroll> {

	public List<TempUnregPayroll> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId);

	public TempUnregPayroll getEntityByEmpDataIdAndUnregSalaryIdAndSpecificModelComponent(Long empDataId, Long unregSalaryId, Integer specific);

	public void deleteByUnregSalaryId(Long unregSalaryId);

	public List<UnregSalaryCalculationExecuteModel> getByParamUnregSalaryId(Long unregSalaryId, int first, int pageSize, Order orderable);

	public Long getTotalByParamUnregSalaryId(Long unregSalaryId);

}
