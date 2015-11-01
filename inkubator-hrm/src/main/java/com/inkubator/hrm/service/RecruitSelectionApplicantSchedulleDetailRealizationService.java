package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;
import com.inkubator.hrm.web.model.SelectionApplicantSchedulleDetailRealizationModel;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantSchedulleDetailRealizationService extends IService<RecruitSelectionApplicantSchedulleDetailRealization> {

	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(Long applicantId, Long selectionApplicantSchedulleId) throws Exception;
	
	public List<SelectionApplicantSchedulleDetailRealizationModel> getAllDataSelectionScheduleRealization(Long applicantId, Long selectionApplicantSchedulleId) throws Exception;
	
	public void saveOrUpdate(List<SelectionApplicantSchedulleDetailRealizationModel> listModel) throws Exception;
	
}
