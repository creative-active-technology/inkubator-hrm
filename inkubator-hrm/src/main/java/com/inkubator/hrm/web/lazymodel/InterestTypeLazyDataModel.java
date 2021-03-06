/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.InterestType;
import com.inkubator.hrm.service.InterestTypeService;
import com.inkubator.hrm.web.search.InterestTypeSearchParameter;
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
 * @author Deni
 */
public class InterestTypeLazyDataModel extends LazyDataModel<InterestType> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(InterestTypeLazyDataModel.class);
    private final InterestTypeSearchParameter search;
    private final InterestTypeService service;
    private List<InterestType> interestTypeList = new ArrayList<>();
    private Integer jumlahData;

    public InterestTypeLazyDataModel(InterestTypeSearchParameter search, InterestTypeService service) {
        this.search = search;
        this.service = service;
    }
    
    @Override
    public List<InterestType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                interestTypeList = service.getByParam(search, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalInterestTypeByParam(search)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return interestTypeList;
    }
    
    @Override
    public Object getRowKey(InterestType interestType) {
        return interestType.getId();
    }

    @Override
    public InterestType getRowData(String id) {
        for (InterestType interestType : interestTypeList) {
            if (id.equals(String.valueOf(interestType.getId()))) {
                return interestType;
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
