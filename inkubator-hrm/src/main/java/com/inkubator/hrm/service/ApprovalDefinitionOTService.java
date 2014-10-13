/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ApprovalDefinitionOTService extends IService<ApprovalDefinitionOT>{
    public List<ApprovalDefinitionOT> getByOverTimeId(Long id) throws Exception;
}
