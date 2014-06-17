/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.web.search.DepartmentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface DepartmentService extends IDAO<Department> {
    public List<Department> getByParam(DepartmentSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalDepartmentByParam(DepartmentSearchParameter searchParameter);

    public Department getByRoleName(String name);
}
