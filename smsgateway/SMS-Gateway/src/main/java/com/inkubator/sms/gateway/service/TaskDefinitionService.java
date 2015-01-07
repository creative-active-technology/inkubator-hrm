/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface TaskDefinitionService extends IService<TaskDefinition> {

    public List<TaskDefinition> getAllByFullTextService(String parameter, int minResult, int maxResult, Order order) throws Exception;

    public Integer getTotalByFullTextService(String parameter) throws Exception;
}
