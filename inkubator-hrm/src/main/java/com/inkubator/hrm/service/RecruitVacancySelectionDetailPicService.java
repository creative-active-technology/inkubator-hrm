package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitVacancySelectionDetailPic;

public interface RecruitVacancySelectionDetailPicService extends IService<RecruitVacancySelectionDetailPic> {
	public List<RecruitVacancySelectionDetailPic> getAllDataByRecruitVacancySelectionDetailId(Long id) throws Exception;
    
}