package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalActivity;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalActivityService extends IService<ApprovalActivity> {

    public void approved(Long appActivityId, String comment) throws Exception;

    public void rejected(Long appActivityId, String comment) throws Exception;
    
    public List<ApprovalActivity> getRequestHistory(String userName)throws Exception;

}
