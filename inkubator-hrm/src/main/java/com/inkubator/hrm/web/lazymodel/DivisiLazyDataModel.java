/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.service.DivisiService;
import com.inkubator.hrm.web.search.DivisiSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EKA
 */
public class DivisiLazyDataModel extends LazyDataModel<Divisi> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(DivisiLazyDataModel.class);
    private final DivisiSearchParameter searchParameter;
    private final DivisiService service;
    private List<Divisi> divisiList = new ArrayList<>();
    private Integer jumlah;
    
    public DivisiLazyDataModel(DivisiSearchParameter searchParameter, DivisiService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<Divisi> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("name");
            }
            divisiList = service.getByParam(searchParameter, first, pageSize, order);
            jumlah = Integer.parseInt(String.valueOf(service.getTotalDivisiByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return divisiList;
    }
    
    @Override
    public Object getRowKey(Divisi divisi){
        return divisi.getId();
    }
    
    @Override
    public Divisi getRowData(String id){
        for(Divisi divisi : divisiList){
            if(id.equals(String.valueOf(divisi.getId()))){
                return divisi;
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
