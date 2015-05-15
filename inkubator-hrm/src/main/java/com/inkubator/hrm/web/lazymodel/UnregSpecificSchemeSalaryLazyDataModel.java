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

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;

/**
 *
 * @author rizkykojek
 */
public class UnregSpecificSchemeSalaryLazyDataModel extends LazyDataModel<UnregSalary> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(UnregSpecificSchemeSalaryLazyDataModel.class);
    private final UnregSalarySearchParameter searchParameter;
    private final UnregSalaryService service;
    private List<UnregSalary> unregSalaryList = new ArrayList<>();
    private Integer jumlahData;

    public UnregSpecificSchemeSalaryLazyDataModel(UnregSalarySearchParameter searchParameter, UnregSalaryService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<UnregSalary> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                unregSalaryList = service.getAllDataComponentByParam(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalComponentByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return unregSalaryList;
    }
    
    @Override
    public Object getRowKey(UnregSalary unregSalary) {
        return unregSalary.getId();
    }

    @Override
    public UnregSalary getRowData(String id) {
        for (UnregSalary unregSalary : unregSalaryList) {
            if (id.equals(String.valueOf(unregSalary.getId()))) {
                return unregSalary;
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
