/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.web.search.SchedulerLogSearchParameter;

/**
 *
 * @author denifahri
 */
public interface SchedulerLogDao extends IDAO<SchedulerLog>{
	public List<SchedulerLog> getByParam(SchedulerLogSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalByParam(SchedulerLogSearchParameter searchParameter);
}
