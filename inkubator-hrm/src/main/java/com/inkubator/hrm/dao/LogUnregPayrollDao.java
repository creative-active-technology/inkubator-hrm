package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogUnregPayroll;
import com.inkubator.hrm.web.model.UnregPayrollViewModel;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LogUnregPayrollDao extends IDAO<LogUnregPayroll> {

	public void deleteByUnregSalaryId(Long unregSalaryId);
	
	public Long getTotalEmployeeByUnregSalaryId(Long unregSalaryId);
	
	public BigDecimal getTotalTakeHomePayByUnregSalaryId(Long unregSalaryId);
	
	public List<LogUnregPayroll> getAllDataByEmpDataIdAndUnregSalaryId(Long empDataId, Long unregSalaryId);

	public List<UnregPayrollViewModel> getByParam(UnregPayrollSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(UnregPayrollSearchParameter parameter);

	public Collection<Long> getAllDataEmpIdByParam(UnregPayrollSearchParameter searchParameter);

}
