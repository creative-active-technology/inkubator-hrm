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

import com.inkubator.hrm.entity.CarreerAwardType;
import com.inkubator.hrm.service.CarreerAwardTypeService;
import com.inkubator.hrm.web.search.CarreerAwardTypeSearchParameter;

/**
 *
 * @author EKA
 */
public class CarreerAwardTypeLazyDataModel extends LazyDataModel<CarreerAwardType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(DivisiLazyDataModel.class);
    private final CarreerAwardTypeSearchParameter searchParameter;
    private final CarreerAwardTypeService service;
    private List<CarreerAwardType> carreerAwardTypeList = new ArrayList<>();
    private Integer jumlah;
    
    public CarreerAwardTypeLazyDataModel(CarreerAwardTypeSearchParameter searchParameter, CarreerAwardTypeService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<CarreerAwardType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            carreerAwardTypeList = service.getByParam(searchParameter, first, pageSize, order);
            jumlah = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
            System.out.println("jumlah list =============== " + carreerAwardTypeList.size());
            System.out.println("total ======================== " + jumlah);
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return carreerAwardTypeList;
    }
    
    @Override
    public Object getRowKey(CarreerAwardType carreerAwardType){
        return carreerAwardType.getId();
    }
    
    @Override
    public CarreerAwardType getRowData(String id){
        for(CarreerAwardType carreerAwardType : carreerAwardTypeList){
            if(id.equals(String.valueOf(carreerAwardType.getId()))){
                return carreerAwardType;
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
