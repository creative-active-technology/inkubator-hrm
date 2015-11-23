package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsApplicationDisbursement;
import com.inkubator.hrm.entity.RmbsDisbursement;
import com.inkubator.hrm.web.search.RmbsDisbursementSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsDisbursementService extends IService<RmbsDisbursement>, BaseApprovalService {

	public String disbursementWithApproval(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement) throws Exception;
	
	public String disbursementWithRevised(List<Long> listRmbsApplicationId, RmbsDisbursement disbursement, Long approvalActivityId) throws Exception;
	
	public List<RmbsApplicationDisbursement> getByParam(RmbsDisbursementSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
	
	public Long getTotalByParam(RmbsDisbursementSearchParameter parameter) throws Exception;
	
}
