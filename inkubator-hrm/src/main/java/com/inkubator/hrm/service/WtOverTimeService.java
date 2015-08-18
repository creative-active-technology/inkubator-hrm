/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.web.search.WtOverTimeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtOverTimeService extends IService<WtOverTime> {

    public List<WtOverTime> getByParam(WtOverTimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalWtOverTimeByParam(WtOverTimeSearchParameter searchParameter) throws Exception;
    
    public void save(WtOverTime entity, List<ApprovalDefinition> appDefs) throws Exception;
    
    public void update(WtOverTime entity, List<ApprovalDefinition> appDefs) throws Exception;
    
    public WtOverTime getEntityByPkFetchApprovalDefinition(Long id) throws Exception;

	public WtOverTime getEntityByPkWithDetail(Long id) throws Exception;
    
}
