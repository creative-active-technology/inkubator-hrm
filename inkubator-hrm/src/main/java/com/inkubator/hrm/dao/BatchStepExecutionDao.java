/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BatchStepExecution;
import java.util.List;

/**
 *
 * @author EKA
 */
public interface BatchStepExecutionDao extends IDAO<BatchStepExecution>{
    
    public List<BatchStepExecution> getExitMessageByJobId(Long id);
    
    public Long getTotalExitMessageByParam(Long id);
    
}
