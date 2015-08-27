package com.inkubator.hrm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsCancelation;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationService extends IService<RmbsApplication>, BaseApprovalService {
	
	//functions
	public String saveWithApproval(RmbsApplication entity, UploadedFile reimbursmentFile) throws Exception;
	
	public String saveWithRevised(RmbsApplication entity, UploadedFile reimbursmentFile, Long approvalActivityId) throws Exception;
	
	public void cancelled(long approvalActivityId, RmbsCancelation rmbsCancelation) throws Exception;	
	
            
	//return entity/property
	public BigDecimal getTotalNominalForOneMonth(Date date, Long empDataId, Long rmbsTypeId) throws Exception;

	public UploadedFile convertFileToUploadedFile(String pendingData);
	
	public RmbsApplication getEntityByPkWithDetail(Long id);
	
	
	
	//return collections
	public List<EmpData> getListApproverByEmpDataId(Long empDataId)  throws Exception;
	
	public HashMap<Long, String> getAllDataNotApprovedYet(String userId) throws Exception;
	
	
	
	//pageable
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter) throws Exception;
	
	public List<RmbsApplication> getUndisbursedByParam(int firstResult, int maxResults, Order orderable);

	public Long getTotalUndisbursedByParam();

}
	