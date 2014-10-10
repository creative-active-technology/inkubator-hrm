package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalDefinitionLeaveDao extends IDAO<ApprovalDefinitionLeave> {
    public List<ApprovalDefinitionLeave> getByLeaveId(Long id);
}
