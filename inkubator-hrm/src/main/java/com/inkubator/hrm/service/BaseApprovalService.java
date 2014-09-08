package com.inkubator.hrm.service;


/**
 *
 * @author rizkykojek
 */
public interface BaseApprovalService {

	public void approved(long approvalActivityId, String comment) throws Exception;
	
	public void rejected(long approvalActivityId, String comment) throws Exception;
	
}
