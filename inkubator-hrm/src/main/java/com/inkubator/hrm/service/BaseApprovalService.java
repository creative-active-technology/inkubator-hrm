package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.ApprovalActivity;


/**
 *
 * @author rizkykojek
 */
public interface BaseApprovalService {

	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception;
	
	public void rejected(long approvalActivityId, String comment) throws Exception;
	
	public void diverted(long approvalActivityId) throws Exception;
	
	public void sendingEmailApprovalNotif(ApprovalActivity appActivity) throws Exception;
	
}
