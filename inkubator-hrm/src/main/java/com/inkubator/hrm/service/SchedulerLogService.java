/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.web.search.SchedulerLogSearchParameter;

/**
 *
 * @author denifahri
 */
public interface SchedulerLogService extends IService<SchedulerLog> {

    public List<SchedulerLog> getByParam(SchedulerLogSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(SchedulerLogSearchParameter searchParameter) throws Exception;

    public void saveBeforeJobBatch() throws Exception;
}
