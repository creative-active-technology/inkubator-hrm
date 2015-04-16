package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsDisbursement;

/**
 *
 * @author rizkykojek
 */
public interface RmbsDisbursementService extends IService<RmbsDisbursement>, BaseApprovalService {

	public String disbursementWithApproval(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement) throws Exception;
	
	public String disbursementWithRevised(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement, Long approvalActivityId) throws Exception;
}
