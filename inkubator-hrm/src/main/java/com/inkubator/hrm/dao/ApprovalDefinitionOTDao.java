/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import java.util.List;

/**
 *
 * @author Deni
 */

public interface ApprovalDefinitionOTDao extends IDAO<ApprovalDefinitionOT> {
    public List<ApprovalDefinitionOT> getByOverTimeId(Long id);
}
