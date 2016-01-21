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

import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.model.PerformanceIndicatorJabatanViewModel;
import com.inkubator.hrm.web.search.PerformanceIndicatorJabatanSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class PerformanceIndicatorJabatanLazyDataModel extends LazyDataModel<PerformanceIndicatorJabatanViewModel> implements Serializable{
	
    private static final Logger LOGGER = Logger.getLogger(PerformanceIndicatorJabatanLazyDataModel.class);
    private final PerformanceIndicatorJabatanSearchParameter searchParameter;
    private final JabatanService jabatanService;
    private List<PerformanceIndicatorJabatanViewModel> list = new ArrayList<>();
    private Integer total;
    
    public PerformanceIndicatorJabatanLazyDataModel(PerformanceIndicatorJabatanSearchParameter searchParameter, JabatanService jabatanService){
        this.searchParameter = searchParameter;
        this.jabatanService = jabatanService;
    }
    
    @Override
    public List<PerformanceIndicatorJabatanViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("jabatan.id");
            }
            list = jabatanService.getByParamForPerformanceIndicatorJabatan(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(jabatanService.getTotalByParamForPerformanceIndicatorJabatan(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(PerformanceIndicatorJabatanViewModel model){
        return model.getJabatanId();
    }
    
    @Override
    public PerformanceIndicatorJabatanViewModel getRowData(String id){
        for(PerformanceIndicatorJabatanViewModel model : list){
        	if(StringUtils.equals(id, String.valueOf(model.getJabatanId()))){
                return model;
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
