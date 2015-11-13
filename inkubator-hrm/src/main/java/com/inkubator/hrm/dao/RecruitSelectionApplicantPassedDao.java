package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantPassedDao extends IDAO<RecruitSelectionApplicantPassed> {

    public List<RecruitSelectionApplicantPassed>getAllWithPlacementStatus(String status);

	public RecruitSelectionApplicantPassed getEntityByPk(RecruitSelectionApplicantPassedId id);

	public Long getTotalByHireApplyIdAndPlacementStatus(Long hireApplyId, String placementStatus);
	
	public List<RecruitSelectionApplicantPassedViewModel> getListSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);
	
	public Long getTotalSelectionPassedViewModelByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter);
}
