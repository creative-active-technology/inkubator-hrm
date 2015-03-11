package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;
import com.inkubator.hrm.web.search.UnregCalculationSearchParameter;

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
	
	public List<TempUnregPayroll> getByParam(UnregCalculationSearchParameter parameter, int first, int pageSize, Order orderable);

	public Long getTotalByParam(UnregCalculationSearchParameter parameter);
	
	public Long getTotalEmployeeByUnregSalaryIdAndPaySalaryCompId(Long unregSalaryId, Long paySalaryComponentId);
	
	public BigDecimal getTotalNominalByUnregSalaryIdAndPaySalaryCompId(Long unregSalaryId, Long paySalaryComponentId);
	
	public Long getTotalByUnregSalaryId(Long unregSalaryId);
	
	public List<EmpData> getAllDataEmployeeByUnregSalaryId(Long unregSalaryId);

}
