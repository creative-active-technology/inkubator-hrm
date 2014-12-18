/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BatchJobExecution;
import com.inkubator.hrm.web.search.BatchJobExecutionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface BatchJobExecutionService extends IService<BatchJobExecution>{
    public List<BatchJobExecution> getByParam(BatchJobExecutionSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
    public Long getTotalByParam(BatchJobExecutionSearchParameter parameter) throws Exception;
}
