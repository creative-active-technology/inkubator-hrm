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

import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.service.AppraisalCompetencyUnitService;
import com.inkubator.hrm.web.search.CompetencyUnitSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class CompetencyUnitLazyDataModel extends LazyDataModel<AppraisalCompetencyUnit> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(AppraisalCompetencyUnit.class);
    private final CompetencyUnitSearchParameter searchParameter;
    private final AppraisalCompetencyUnitService appraisalCompetencyUnitService;
    private List<AppraisalCompetencyUnit> list = new ArrayList<>();
    private Integer total;
    
    public CompetencyUnitLazyDataModel(CompetencyUnitSearchParameter searchParameter, AppraisalCompetencyUnitService appraisalCompetencyUnitService){
        this.searchParameter = searchParameter;
        this.appraisalCompetencyUnitService = appraisalCompetencyUnitService;
    }
    
    @Override
    public List<AppraisalCompetencyUnit> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            list = appraisalCompetencyUnitService.getAllDataByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(appraisalCompetencyUnitService.getTotalByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(AppraisalCompetencyUnit appraisalCompetencyUnit){
        return appraisalCompetencyUnit.getId();
    }
    
    @Override
    public AppraisalCompetencyUnit getRowData(String id){
        for(AppraisalCompetencyUnit appraisalCompetencyUnit : list){
        	if(StringUtils.equals(id, String.valueOf(appraisalCompetencyUnit.getId()))){
                return appraisalCompetencyUnit;
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
