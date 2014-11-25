/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.web.search.CostCenterDeptSearchParameter;

import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface CostCenterDeptService extends IService<CostCenterDept> {
	
    public List<CostCenterDept> getByParam(CostCenterDeptSearchParameter parameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(CostCenterDeptSearchParameter parameter) throws Exception;
    
    public CostCenterDept getEntityByIdWithDetail(Long id) throws Exception;
}
