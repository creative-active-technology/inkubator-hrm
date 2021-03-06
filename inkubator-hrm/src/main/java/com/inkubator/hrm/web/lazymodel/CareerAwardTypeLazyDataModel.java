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

import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.service.CareerAwardTypeService;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;

/**
 *
 * @author EKA
 */
public class CareerAwardTypeLazyDataModel extends LazyDataModel<CareerAwardType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(DivisiLazyDataModel.class);
    private final CareerAwardTypeSearchParameter searchParameter;
    private final CareerAwardTypeService service;
    private List<CareerAwardType> careerAwardTypeList = new ArrayList<>();
    private Integer jumlah;
    
    public CareerAwardTypeLazyDataModel(CareerAwardTypeSearchParameter searchParameter, CareerAwardTypeService service){
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<CareerAwardType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
        LOGGER.info("Step Load Lazy Data Model");
        try{
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            } else {
                order = Order.desc("id");
            }
            careerAwardTypeList = service.getByParam(searchParameter, first, pageSize, order);
            jumlah = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
        LOGGER.info("Success Load Lazy Data Model");
        
        setPageSize(pageSize);
        setRowCount(jumlah);
        return careerAwardTypeList;
    }
    
    @Override
    public Object getRowKey(CareerAwardType careerAwardType){
        return careerAwardType.getId();
    }
    
    @Override
    public CareerAwardType getRowData(String id){
        for(CareerAwardType careerAwardType : careerAwardTypeList){
            if(id.equals(String.valueOf(careerAwardType.getId()))){
                return careerAwardType;
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
