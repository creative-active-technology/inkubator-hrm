package com.inkubator.hrm.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationService extends IService<RmbsApplication>, BaseApprovalService {
	
	public String saveWithApproval(RmbsApplication entity, UploadedFile reimbursmentFile) throws Exception;
	
	public String saveWithRevised(RmbsApplication entity, UploadedFile reimbursmentFile, Long approvalActivityId) throws Exception;

	public BigDecimal getTotalNominalByThisMonth(Long empDataId, Long rmbsTypeId) throws Exception;

	public UploadedFile convertFileToUploadedFile(String pendingData);
	
	public List<EmpData> getListApproverByEmpDataId(Long empDataId)  throws Exception;
	
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter) throws Exception;
	
	public RmbsApplication getEntityByPkWithDetail(Long id);

}
	