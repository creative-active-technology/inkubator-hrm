package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmployeeType;

/**
 *
 * @author rizkykojek
 */
public interface EmployeeTypeService extends IService<EmployeeType> {

    public List<EmployeeType> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(String parameter) throws Exception;

    public EmployeeType getEntityByUnregSalaryIdWithDetail(Long unregSalaryId) throws Exception;
    
    public List<String> getEmployeeTypeNameByPk() throws Exception;
}
