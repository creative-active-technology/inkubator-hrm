/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.web.search.SchedulerConfigSearchParameter;

/**
 *
 * @author denifahri
 */
public interface SchedulerConfigDao extends IDAO<SchedulerConfig> {

    public List<SchedulerConfig> getByParam(SchedulerConfigSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(SchedulerConfigSearchParameter searchParameter);

    public List<SchedulerConfig> getAllWithIsTimeDiv(Boolean boolean1);
}
