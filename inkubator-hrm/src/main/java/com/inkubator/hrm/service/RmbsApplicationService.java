package com.inkubator.hrm.service;

import java.math.BigDecimal;

import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RmbsApplication;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationService extends IService<RmbsApplication>, BaseApprovalService {
	
	public String save(RmbsApplication entity, UploadedFile reimbursmentFile, boolean isBypassApprovalChecking) throws Exception;

	public BigDecimal getTotalNominalByThisMonth(Long empDataId, Long rmbsTypeId) throws Exception;

}
	