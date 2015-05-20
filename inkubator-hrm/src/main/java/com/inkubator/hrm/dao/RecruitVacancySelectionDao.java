/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.web.search.RecruitVacancySelectionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitVacancySelectionDao extends IDAO<RecruitVacancySelection> {
    public List<RecruitVacancySelection> getByParam(RecruitVacancySelectionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(RecruitVacancySelectionSearchParameter searchParameter);
}