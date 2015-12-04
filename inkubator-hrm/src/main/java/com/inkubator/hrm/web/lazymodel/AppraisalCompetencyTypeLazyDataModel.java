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

import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.web.search.AppraisalCompetencyTypeSearchParameter;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class AppraisalCompetencyTypeLazyDataModel extends LazyDataModel<AppraisalCompetencyType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(DivisiLazyDataModel.class);
    private final AppraisalCompetencyTypeSearchParameter searchParameter;
    private final AppraisalCompetencyTypeService service;
    private List<AppraisalCompetencyType> appraisalCompetencyTypesList = new ArrayList<>();
    private Integer jumlah;
    
    public AppraisalCompetencyTypeLazyDataModel(AppraisalCompetencyTypeSearchParameter searchParameter, AppraisalCompetencyTypeService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<AppraisalCompetencyType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            appraisalCompetencyTypesList = service.getListByParam(searchParameter, first, pageSize, order);
            jumlah = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return appraisalCompetencyTypesList;
    }
    
    @Override
    public Object getRowKey(AppraisalCompetencyType appraisalCompetencyType){
        return appraisalCompetencyType.getId();
    }
    
    @Override
    public AppraisalCompetencyType getRowData(String id){
        for(AppraisalCompetencyType appraisalCompetencyType : appraisalCompetencyTypesList){
        	if(StringUtils.equals(id, String.valueOf(appraisalCompetencyType.getId()))){
                return appraisalCompetencyType;
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
