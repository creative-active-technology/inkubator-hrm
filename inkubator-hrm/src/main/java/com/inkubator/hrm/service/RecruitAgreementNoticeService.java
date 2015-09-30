package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitAgreementNotice;

public interface RecruitAgreementNoticeService extends IService<RecruitAgreementNotice>{
	public RecruitAgreementNotice getEntityByBioDataId(Long id) throws Exception;
}
