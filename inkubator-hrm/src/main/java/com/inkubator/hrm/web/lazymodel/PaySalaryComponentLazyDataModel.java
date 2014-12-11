/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;
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
public class PaySalaryComponentLazyDataModel extends LazyDataModel<PaySalaryComponent> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SavingTypeLazyDataModel.class);
    private final PaySalaryComponentSearchParameter searchParameter;
    private final PaySalaryComponentService service;
    private List<PaySalaryComponent> paySalaryComponentList = new ArrayList<>();
    private Integer jumlahData;

    public PaySalaryComponentLazyDataModel(PaySalaryComponentSearchParameter searchParameter, PaySalaryComponentService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PaySalaryComponent> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("name");
                }
                paySalaryComponentList = service.getByParamWithDetail(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalResourceTypeByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return paySalaryComponentList;
    }
    
    @Override
    public Object getRowKey(PaySalaryComponent paySalaryComponent) {
        return paySalaryComponent.getId();
    }

    @Override
    public PaySalaryComponent getRowData(String id) {
        for (PaySalaryComponent paySalaryComponent : paySalaryComponentList) {
            if (id.equals(String.valueOf(paySalaryComponent.getId()))) {
                return paySalaryComponent;
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
