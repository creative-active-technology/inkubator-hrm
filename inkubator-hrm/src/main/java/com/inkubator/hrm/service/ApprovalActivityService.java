package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalActivity;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalActivityService extends IService<ApprovalActivity> {

    public List<ApprovalActivity> getRequestHistory(String userName) throws Exception;

    public List<ApprovalActivity> getReguestHistoryById(long id) throws Exception;

    public List<ApprovalActivity> getPendingRequest(String userName) throws Exception;

    public List<ApprovalActivity> getPendingTask(String userName) throws Exception;

}
