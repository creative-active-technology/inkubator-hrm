/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ApprovalDefinitionLeaveService extends IService<ApprovalDefinitionLeave>{
    public List<ApprovalDefinitionLeave> getByLeaveId(Long id) throws Exception;
}
