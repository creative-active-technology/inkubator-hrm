/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.web.model.UnregSalaryModel;
import com.inkubator.hrm.web.model.UnregSalaryViewModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deni
 */
public interface UnregSalaryService extends IService<UnregSalary> {
    public List<UnregSalary> getByParam(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public List<UnregSalaryViewModel> getByParamWithViewModel(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
            
    public Long getTotalByParam(UnregSalarySearchParameter searchParameter) throws Exception;
    
    public void saved(UnregSalaryModel model) throws Exception;
    
    public void updated(UnregSalaryModel model) throws Exception;
    
    public UnregSalary getEntityByPkWithDetail(Long id) throws Exception;
    
    public void save(Long unregSalaryId, Date startDate, Date endDate, List<GolonganJabatan> golonganJabatans, List<Department> departments, List<Religion> religions, List<EmployeeType> employeeTypes) throws Exception;
    
    public Long getTotalByParamViewModel(UnregSalarySearchParameter searchParameter) throws Exception;
}
