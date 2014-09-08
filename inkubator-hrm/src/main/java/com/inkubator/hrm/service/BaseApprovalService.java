package com.inkubator.hrm.service;

import com.google.gson.GsonBuilder;


/**
 *
 * @author rizkykojek
 */
public interface BaseApprovalService {

	public void approved(long approvalActivityId, String comment) throws Exception;
	
	public void rejected(long approvalActivityId, String comment) throws Exception;
	
	public GsonBuilder getGsonBuilder();
	
}
