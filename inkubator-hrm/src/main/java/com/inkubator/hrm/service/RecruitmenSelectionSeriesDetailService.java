/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitmenSelectionSeriesDetailService extends IService<RecruitmenSelectionSeriesDetail> {
    public List<RecruitmenSelectionSeriesDetail> getByParam(int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalBySelectionSeriesId(Long id) throws Exception;
    
    public List<RecruitmenSelectionSeriesDetail> getAllDataBySelectionSeriesId(Long id, int firstResult, int maxResults, Order order) throws Exception;

    public void doChangerListOrder(int newGradeLevel, RecruitmenSelectionSeriesDetailId id, Long recSelectionSeriesId) throws Exception;
    
    public RecruitmenSelectionSeriesDetail getEntityByPk(RecruitmenSelectionSeriesDetailId id) throws Exception;

    public List<RecruitmenSelectionSeriesDetail> getEntityBySelectionTypeId(Long id) throws Exception;
}