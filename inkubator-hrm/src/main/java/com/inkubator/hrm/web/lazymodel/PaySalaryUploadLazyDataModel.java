/*
 * To change this template, choose Tools | Templates
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
 * @author rizkykojek
 */
public class PaySalaryUploadLazyDataModel extends LazyDataModel<PaySalaryComponent> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PaySalaryUploadLazyDataModel.class);
    private final PaySalaryComponentSearchParameter parameter;
    private final PaySalaryComponentService paySalaryComponentService;
    private List<PaySalaryComponent> list = new ArrayList<>();
    private Integer total;
    
    public PaySalaryUploadLazyDataModel(PaySalaryComponentSearchParameter paySalaryComponentSearchParameter, PaySalaryComponentService paySalaryComponentService) {
        this.parameter = paySalaryComponentSearchParameter;
        this.paySalaryComponentService = paySalaryComponentService;
    }
    
    @Override
    public List<PaySalaryComponent> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    	LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = paySalaryComponentService.getAllDataComponentUploadByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(paySalaryComponentService.getTotalComponentUploadByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }
    
    @Override
    public Object getRowKey(PaySalaryComponent paySalaryComponent) {
        return paySalaryComponent.getId();
    }

    @Override
    public PaySalaryComponent getRowData(String id) {
        for (PaySalaryComponent paySalaryComponent : list) {
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
