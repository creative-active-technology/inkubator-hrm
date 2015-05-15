/*
 * To change this template, choose Tools | Templates
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

import com.inkubator.hrm.entity.TaxRate;
import com.inkubator.hrm.service.TaxRateService;

/**
 *
 * @author rizkykojek
 */
public class TaxRateLazyDataModel extends LazyDataModel<TaxRate> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(TaxRateLazyDataModel.class);
    private final TaxRateService taxRateService;
    private List<TaxRate> list = new ArrayList<>();
    private Integer total;
    
    public TaxRateLazyDataModel(TaxRateService taxRateService) {
        this.taxRateService = taxRateService;
    }
    
    @Override
    public List<TaxRate> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    	LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("lowRate");
	        }
	        
	        list = taxRateService.getByParam(first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(taxRateService.getTotalByParam()));            
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
    public Object getRowKey(TaxRate taxRate) {
        return taxRate.getId();
    }

    @Override
    public TaxRate getRowData(String id) {
        for (TaxRate taxRate : list) {
            if (id.equals(String.valueOf(taxRate.getId()))) {
                return taxRate;
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
