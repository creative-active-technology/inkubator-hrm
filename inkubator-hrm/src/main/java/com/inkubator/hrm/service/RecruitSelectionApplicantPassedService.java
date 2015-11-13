package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantPassedService extends IService<RecruitSelectionApplicantPassed> {

	public void save(Long selectionScheduleId, List<Long> listApplicantId) throws Exception;
	
	public Long getTotalByHireApplyIdAndNotPlacementStatus(Long hireApplyId, String placementStatus)  throws Exception;
	
	public List<RecruitSelectionApplicantPassedViewModel> getListSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;
	
	public Long getTotalSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter)  throws Exception;
	
}
