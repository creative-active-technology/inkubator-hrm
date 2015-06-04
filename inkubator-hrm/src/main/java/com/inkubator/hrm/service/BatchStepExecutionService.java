/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BatchStepExecution;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface BatchStepExecutionService extends IService<BatchStepExecution>{
    public List<BatchStepExecution> getExitMessageByJobId(Long id);
    
    public Long getTotalExitMessageById(Long id);
    
}
