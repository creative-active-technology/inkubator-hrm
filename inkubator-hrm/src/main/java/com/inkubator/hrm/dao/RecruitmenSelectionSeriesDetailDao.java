/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitmenSelectionSeriesDetailDao extends IDAO<RecruitmenSelectionSeriesDetail> {
    public List<RecruitmenSelectionSeriesDetail> getByParam(int firstResult, int maxResults, Order order);
    
    public List<RecruitmenSelectionSeriesDetail> getAllDataBySelectionSeriesId(Long id, int firstResult, int maxResults, Order order);

    public Integer getLastIndexBySelectionSeriesId(Long id);
    
    public Long getTotalBySelectionSeriesId(Long id);
    
    public RecruitmenSelectionSeriesDetail getByListOrderAndRecSelectionSeriesId(Integer number, Long id);
    
    public RecruitmenSelectionSeriesDetail getEntityByPk(RecruitmenSelectionSeriesDetailId id);
    
    public Long getTotalByPk(RecruitmenSelectionSeriesDetailId id);
}
