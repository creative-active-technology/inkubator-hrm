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

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.service.UnregPayComponentsExceptionService;
import com.inkubator.hrm.web.search.UnregPayComponentExceptionSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class UnregSpecificSchemeExceptionLazyDataModel extends LazyDataModel<UnregPayComponentsException> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(UnregSpecificSchemeExceptionLazyDataModel.class);
    private final UnregPayComponentExceptionSearchParameter searchParameter;
    private final UnregPayComponentsExceptionService service;
    private List<UnregPayComponentsException> list = new ArrayList<>();
    private Integer total;

    public UnregSpecificSchemeExceptionLazyDataModel(UnregPayComponentExceptionSearchParameter searchParameter, UnregPayComponentsExceptionService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<UnregPayComponentsException> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("nominal");
                }
                list = service.getByParam(searchParameter, first, pageSize, order);
                total = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(UnregPayComponentsException unregPayComponentsException) {
        return unregPayComponentsException.getId();
    }

    @Override
    public UnregPayComponentsException getRowData(String id) {
        for (UnregPayComponentsException unregPayComponentsException : list) {
            if (id.equals(String.valueOf(unregPayComponentsException.getId()))) {
                return unregPayComponentsException;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
    
}
