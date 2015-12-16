/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.service.AppraisalCompetencyGroupService;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class CompetencyGroupLazyDataModel extends LazyDataModel<AppraisalCompetencyGroup> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(AppraisalCompetencyGroup.class);
    private final CompetencyGroupSearchParameter searchParameter;
    private final AppraisalCompetencyGroupService appraisalCompetencyGroupService;
    private List<AppraisalCompetencyGroup> list = new ArrayList<>();
    private Integer total;
    
    public CompetencyGroupLazyDataModel(CompetencyGroupSearchParameter searchParameter, AppraisalCompetencyGroupService appraisalCompetencyGroupService){
        this.searchParameter = searchParameter;
        this.appraisalCompetencyGroupService = appraisalCompetencyGroupService;
    }
    
    @Override
    public List<AppraisalCompetencyGroup> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            list = appraisalCompetencyGroupService.getAllDataByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(appraisalCompetencyGroupService.getTotalByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(AppraisalCompetencyGroup appraisalCompetencyGroup){
        return appraisalCompetencyGroup.getId();
    }
    
    @Override
    public AppraisalCompetencyGroup getRowData(String id){
        for(AppraisalCompetencyGroup appraisalCompetencyGroup : list){
        	if(StringUtils.equals(id, String.valueOf(appraisalCompetencyGroup.getId()))){
                return appraisalCompetencyGroup;
            }
        }
        return null;
    }
    
    @Override
    public void setRowIndex(int rowIndex){
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
