/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.web.model.RecruitVacancySelectionModel;
import com.inkubator.hrm.web.search.RecruitVacancySelectionSearchParameter;

/**
 *
 * @author Deni
 */
public interface RecruitVacancySelectionService extends IService<RecruitVacancySelection> {
    public List<RecruitVacancySelection> getByParam(RecruitVacancySelectionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(RecruitVacancySelectionSearchParameter searchParameter) throws Exception;
    
    public void saveRecruitVacancySelectionSeries(RecruitVacancySelectionModel model);
    
    public RecruitVacancySelection getEntityByPkWithDetail(Long id) throws Exception;
    
}
