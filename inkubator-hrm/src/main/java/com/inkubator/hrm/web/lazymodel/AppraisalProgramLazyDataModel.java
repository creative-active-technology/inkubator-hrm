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

import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class AppraisalProgramLazyDataModel extends LazyDataModel<AppraisalProgram> implements Serializable{
	
    private static final Logger LOGGER = Logger.getLogger(AppraisalProgramLazyDataModel.class);
    private final AppraisalProgramSearchParameter searchParameter;
    private final AppraisalProgramService appraisalProgramService;
    private List<AppraisalProgram> list = new ArrayList<>();
    private Integer total;
    
    public AppraisalProgramLazyDataModel(AppraisalProgramSearchParameter searchParameter, AppraisalProgramService appraisalProgramService){
        this.searchParameter = searchParameter;
        this.appraisalProgramService = appraisalProgramService;
    }
    
    @Override
    public List<AppraisalProgram> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            list = appraisalProgramService.getAllDataByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(appraisalProgramService.getTotalByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(AppraisalProgram appraisalProgram){
        return appraisalProgram.getId();
    }
    
    @Override
    public AppraisalProgram getRowData(String id){
        for(AppraisalProgram appraisalProgram : list){
        	if(StringUtils.equals(id, String.valueOf(appraisalProgram.getId()))){
                return appraisalProgram;
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
