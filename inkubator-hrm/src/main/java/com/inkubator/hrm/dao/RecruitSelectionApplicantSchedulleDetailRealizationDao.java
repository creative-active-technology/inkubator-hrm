package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantSchedulleDetailRealizationDao extends IDAO<RecruitSelectionApplicantSchedulleDetailRealization> {

	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
			Long applicantId, Long selectionApplicantSchedulleId);

	public RecruitSelectionApplicantSchedulleDetailRealization getEntityBySelectionApplicantSchedulleDetailId(Long selectionApplicantSchedulleDetailId);
	
	public Boolean isSchedulleDetailHaveBeenRealized(Long recruitSelectionApplicantScheduleDetailId);
	
}
