package com.inkubator.hrm.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.primefaces.model.UploadedFile;

import com.google.gson.Gson;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsApplication;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationService extends IService<RmbsApplication>, BaseApprovalService {
	
	public String saveWithApproval(RmbsApplication entity, UploadedFile reimbursmentFile) throws Exception;
	
	public String saveWithRevised(RmbsApplication entity, UploadedFile reimbursmentFile, Long approvalActivityId) throws Exception;

	public BigDecimal getTotalNominalByThisMonth(Long empDataId, Long rmbsTypeId) throws Exception;

	public UploadedFile convertFileToUploadedFile(Gson gson, String pendingData) throws IOException;

}
	