package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;

import java.util.List;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantPassedDao extends IDAO<RecruitSelectionApplicantPassed> {

    public List<RecruitSelectionApplicantPassed>getAllWithPlacementStatus(String status);

	public RecruitSelectionApplicantPassed getEntityByPk(RecruitSelectionApplicantPassedId id);

	public Long getTotalByHireApplyIdAndPlacementStatus(Long hireApplyId, String placementStatus);
}
