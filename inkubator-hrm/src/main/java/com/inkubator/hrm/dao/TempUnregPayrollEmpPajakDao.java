package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.web.model.UnregPayrollEmpPajakModel;
import com.inkubator.hrm.web.search.UnregPayrollEmpPajakSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface TempUnregPayrollEmpPajakDao extends IDAO<TempUnregPayrollEmpPajak> {

	public TempUnregPayrollEmpPajak getEntityByEmpDataIdAndUnregSalaryIdAndTaxComponentId(Long empDataId, Long unregSalaryId, Long taxCompId);

	public void deleteByUnregSalaryId(Long unregSalaryId);

	public List<UnregPayrollEmpPajakModel> getAllDataGroupingTaxCompByUnregSalaryId(Long unregSalaryId);

	public List<TempUnregPayrollEmpPajak> getByParam(UnregPayrollEmpPajakSearchParameter parameter, int first, int pageSize, Order orderable);

	public Long getTotalByParam(UnregPayrollEmpPajakSearchParameter parameter);

}
