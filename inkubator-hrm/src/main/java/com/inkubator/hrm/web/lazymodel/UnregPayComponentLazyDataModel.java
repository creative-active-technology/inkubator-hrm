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

import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.service.UnregPayComponentsService;
import com.inkubator.hrm.web.search.UnregPayComponentsSearchParameter;

/**
 *
 * @author deni
 */
public class UnregPayComponentLazyDataModel extends LazyDataModel<UnregPayComponents> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(UnregPayComponentLazyDataModel.class);
    private final UnregPayComponentsSearchParameter searchParameter;
    private final UnregPayComponentsService service;
    private List<UnregPayComponents> unregPayComponentList = new ArrayList<>();
    private Integer jumlahData;

    public UnregPayComponentLazyDataModel(UnregPayComponentsSearchParameter searchParameter, UnregPayComponentsService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<UnregPayComponents> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("paySalaryComponent");
                }
                unregPayComponentList = service.getByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return unregPayComponentList;
    }
    
    @Override
    public Object getRowKey(UnregPayComponents unregPayComponents) {
        return unregPayComponents.getId();
    }

    @Override
    public UnregPayComponents getRowData(String id) {
        for (UnregPayComponents unregPayComponents : unregPayComponentList) {
            if (id.equals(String.valueOf(unregPayComponents.getId()))) {
                return unregPayComponents;
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
