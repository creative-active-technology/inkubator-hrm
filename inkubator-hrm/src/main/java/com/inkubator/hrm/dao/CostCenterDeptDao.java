/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.web.search.CostCenterDeptSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface CostCenterDeptDao extends IDAO<CostCenterDept>{
	
    public List<CostCenterDept> getByParam(CostCenterDeptSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(CostCenterDeptSearchParameter searchParameter);
    
    public CostCenterDept getEntityByIdWithDetail(Long id);
    
}
