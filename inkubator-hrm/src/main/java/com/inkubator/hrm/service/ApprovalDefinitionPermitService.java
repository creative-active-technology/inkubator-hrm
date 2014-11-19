/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinitionPermit;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ApprovalDefinitionPermitService extends IService<ApprovalDefinitionPermit>{
    public List<ApprovalDefinitionPermit> getByPermitId(Long id) throws Exception;
}