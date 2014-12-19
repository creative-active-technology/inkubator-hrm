/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.web.search.DepartmentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface DepartmentService extends IService<Department> {
	
    public List<Department> getByParam(DepartmentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalDepartmentByParam(DepartmentSearchParameter searchParameter) throws Exception;

    public Long getByDepartmentName(String name) throws Exception;
    
    public Department getEntityByPkWithDetail(Long id) throws Exception;
    
}
