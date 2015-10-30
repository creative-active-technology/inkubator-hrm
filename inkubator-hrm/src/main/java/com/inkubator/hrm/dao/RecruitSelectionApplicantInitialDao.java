/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;

/**
 *
 * @author Deni, Ahmad Mudzakkir Amal
 */
public interface RecruitSelectionApplicantInitialDao extends IDAO<RecruitSelectionApplicantInitial>{
	public Long getTotalApplicantByRecruitHireApplyId(Long recruitHireApplyId);
	
	public List<RecruitmentScheduleSettingViewModel> getByParamForRecruitmentScheduleSetting(RecruitmentScheduleSettingSearchParameter searchParameter,	
			int firstResult, int maxResults, Order orderable);
    
    public Long getTotalByParamforRecruitmentScheduleSetting(RecruitmentScheduleSettingSearchParameter searchParameter);
}
