package com.inkubator.hrm.service;



/**
 *
 * @author rizkykojek
 */
public interface BaseApprovalService {

	public void approved(long approvalActivityId, String pendingDataUpdate, String comment) throws Exception;
	
	public void rejected(long approvalActivityId, String comment) throws Exception;
	
	public void diverted(long approvalActivityId) throws Exception;
	
	public void cancelled(long approvalActivityId, String comment) throws Exception;
	
}
