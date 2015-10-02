/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.web.search.SchedulerConfigSearchParameter;

/**
 *
 * @author denifahri
 */
public interface SchedulerConfigService extends IService<SchedulerConfig>{
	public List<SchedulerConfig> getByParam(SchedulerConfigSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
	
	public Long getTotalByParam(SchedulerConfigSearchParameter searchParameter) throws Exception;
}
