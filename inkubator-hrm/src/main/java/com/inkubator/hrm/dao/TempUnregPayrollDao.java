package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempUnregPayroll;

/**
 *
 * @author rizkykojek
 */
public interface TempUnregPayrollDao extends IDAO<TempUnregPayroll> {

	public List<TempUnregPayroll> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId);

	public TempUnregPayroll getEntityByEmpDataIdAndUnregSalaryIdAndSpecificModelComponent(Long empDataId, Long unregSalaryId, Integer specific);

	public void deleteByUnregSalaryId(Long unregSalaryId);

}
