package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;

/**
 *
 * @author rizkykojek
 */
public interface TempUnregPayrollEmpPajakDao extends IDAO<TempUnregPayrollEmpPajak> {

	public TempUnregPayrollEmpPajak getEntityByEmpDataIdAndUnregSalaryIdAndTaxComponentId(Long empDataId, Long unregSalaryId, Long taxCompId);

	public void deleteByUnregSalaryId(Long unregSalaryId);

}
