/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitSelectionApplicantInitialService extends IService<RecruitSelectionApplicantInitial>{
    public void save(List<Long> listApplicantId) throws Exception;
    
    public List<RecruitmentScheduleSettingViewModel> getByParamForRecruitmentScheduleSetting(RecruitmentScheduleSettingSearchParameter searchParameter, 
    		int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalByParamforRecruitmentScheduleSetting(RecruitmentScheduleSettingSearchParameter searchParameter)  throws Exception;
}
