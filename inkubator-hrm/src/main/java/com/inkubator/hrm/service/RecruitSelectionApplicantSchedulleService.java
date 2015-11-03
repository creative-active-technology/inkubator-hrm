package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.web.model.RecruitScheduleSettingModel;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantSchedulleService extends IService<RecruitSelectionApplicantSchedulle> {

	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id);
	
	public String saveData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail) throws Exception;
	
	public String updateData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail) throws Exception;
	
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId) throws Exception;
	
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId) throws Exception;

}
