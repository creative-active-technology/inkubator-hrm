/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.web.model.UnregSalaryViewModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface UnregSalaryDao extends IDAO<UnregSalary> {
	
    public List<UnregSalary> getByParam(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public List<UnregSalaryViewModel> getByParamWithViewModel(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(UnregSalarySearchParameter searchParameter);
    
    public UnregSalary getEntityByPkWithDetail(Long id);

    public void deleteAllDataByUnregSalaryId(Long unregSalaryId);
    
    public Long getTotalByParamViewModel(UnregSalarySearchParameter searchParameter);
    
    public List<UnregSalary> getByParamBySalaryCalculation(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType, int firstResult, int maxResults, Order order);
    
    public Long getTotalByParamBySalaryCalculation(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType);

	public List<UnregSalary> getByParamBySalaryPayroll(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType, int firstResult, int maxResults, Order order);

	public Long getTotalByParamBySalaryPayroll(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType);

}
