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

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.AppraisalProgramEmpService;
import com.inkubator.hrm.web.search.AppraisalProgramEmployeeSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class AppraisalProgramEmployeeLazyDataModel extends LazyDataModel<EmpData> implements Serializable{
	
    private static final Logger LOGGER = Logger.getLogger(AppraisalProgramEmployeeLazyDataModel.class);
    private final AppraisalProgramEmployeeSearchParameter searchParameter;
    private final AppraisalProgramEmpService appraisalProgramEmpService;
    private List<EmpData> list = new ArrayList<>();
    private Integer total;
    
    public AppraisalProgramEmployeeLazyDataModel(AppraisalProgramEmployeeSearchParameter searchParameter, AppraisalProgramEmpService appraisalProgramEmpService){
        this.searchParameter = searchParameter;
        this.appraisalProgramEmpService = appraisalProgramEmpService;
    }
    
    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            list = appraisalProgramEmpService.getAllEmployeeNotDistributedByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(appraisalProgramEmpService.getTotalEmployeeNotDistributedByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(EmpData empData){
        return empData.getId();
    }
    
    @Override
    public EmpData getRowData(String id){
        for(EmpData empData : list){
        	if(StringUtils.equals(id, String.valueOf(empData.getId()))){
                return empData;
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
