package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantSchedulleDetailService extends IService<RecruitSelectionApplicantSchedulleDetail> {

	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
			Long applicantId, Long selectionApplicantSchedulleId);
	
}
