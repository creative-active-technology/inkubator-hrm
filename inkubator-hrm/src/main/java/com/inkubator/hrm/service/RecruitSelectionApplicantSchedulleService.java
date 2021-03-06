package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantSchedulleService extends IService<RecruitSelectionApplicantSchedulle> {

	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id);
	
	public List<SelectionPositionPassedViewModel> getSelectionPositionPassedByParam(String parameter, int firstResults, int maxResults, Order orderable) throws Exception;
	
	public Long getTotalSelectionPositionPassedByParam(String parameter) throws Exception;
	
	public String saveData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail) throws Exception;
	
	public List<SelectionApplicantPassedViewModel> getSelectionApplicantPassedByParam(Long scheduleId, int firstResults, int maxResults, Order orderable) throws Exception;

	public Long getTotalSelectionApplicantPassedByParam(Long scheduleId) throws Exception;
	
	public String updateData(RecruitSelectionApplicantSchedulle recruitSelectionchedulle, List<RecruitSelectionApplicantSchedulleDetail> listRecruitSelectionScheduleDetail) throws Exception;
	
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId) throws Exception;
	
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId) throws Exception;

}
