package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalActivity;

/**
 *
 * @author rizkykojek
 */
public interface ApprovalActivityService extends IService<ApprovalActivity> {
	
	public ApprovalActivity approved(Long appActivityId, String comment) throws Exception;
	
	public ApprovalActivity rejected(Long appActivityId, String comment) throws Exception;
	
}
