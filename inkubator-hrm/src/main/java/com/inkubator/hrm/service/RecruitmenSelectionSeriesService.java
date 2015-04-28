/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitmenSelectionSeries;
import com.inkubator.hrm.web.search.RecruitmenSelectionSeriesSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitmenSelectionSeriesService extends IService<RecruitmenSelectionSeries> {
    public List<RecruitmenSelectionSeries> getByParam(RecruitmenSelectionSeriesSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(RecruitmenSelectionSeriesSearchParameter searchParameter) throws Exception;
}
