package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantSchedulleService extends IService<RecruitSelectionApplicantSchedulle> {

	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id);

}
