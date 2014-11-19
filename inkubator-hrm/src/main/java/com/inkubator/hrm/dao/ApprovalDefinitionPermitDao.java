/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinitionPermit;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ApprovalDefinitionPermitDao extends IDAO<ApprovalDefinitionPermit> {
    public List<ApprovalDefinitionPermit> getByPermitId(Long id);
}
