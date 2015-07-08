package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetail;

public interface RecruitVacancySelectionDetailService extends IService<RecruitVacancySelectionDetail> {
    public List<RecruitVacancySelectionDetail> getAllDataByRecruitVacancySelection(Long id) throws Exception;
    
    public RecruitVacancySelectionDetail getEntityByRecruitVacancySelection(Long id) throws Exception;
    
}