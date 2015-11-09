package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantPassedService extends IService<RecruitSelectionApplicantPassed> {

	public void save(Long selectionScheduleId, List<Long> listApplicantId) throws Exception;
	
	public Long getTotalByHireApplyIdAndNotPlacementStatus(Long hireApplyId, String placementStatus)  throws Exception;
	
}
