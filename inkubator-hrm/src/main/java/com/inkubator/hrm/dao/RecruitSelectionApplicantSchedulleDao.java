package com.inkubator.hrm.dao;



import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface RecruitSelectionApplicantSchedulleDao extends IDAO<RecruitSelectionApplicantSchedulle> {

	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id);
	
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId);
	
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId);

	public List<SelectionPositionPassedViewModel> getSelectionPositionPassedByParam(String parameter, int firstResults, int maxResults, Order orderable);
	
	public Long getTotalSelectionPositionPassedByParam(String parameter);

	public List<SelectionApplicantPassedViewModel> getSelectionApplicantPassedByParam(Long scheduleId, int firstResults, int maxResults, Order orderable);

	public Long getTotalSelectionApplicantPassedByParam(Long scheduleId);

}
