package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalActivity;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalActivityDao extends IDAO<ApprovalActivity> {

    public List<ApprovalActivity> getRequestHistory(String userName);
}
