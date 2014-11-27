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

import com.inkubator.hrm.entity.PayTempUploadData;
import com.inkubator.hrm.service.PayTempUploadDataService;
import com.inkubator.hrm.web.search.PayTempUploadDataSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class PayTempUploadDataLazyDataModel extends LazyDataModel<PayTempUploadData> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PayTempUploadDataLazyDataModel.class);
    private final PayTempUploadDataSearchParameter parameter;
    private final PayTempUploadDataService payTempUploadDataService;
    private List<PayTempUploadData> list = new ArrayList<>();
    private Integer total;
    
    public PayTempUploadDataLazyDataModel(PayTempUploadDataSearchParameter parameter, PayTempUploadDataService payTempUploadDataService) {
        this.parameter = parameter;
        this.payTempUploadDataService = payTempUploadDataService;
    }
    
    @Override
    public List<PayTempUploadData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
    	LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = payTempUploadDataService.getAllDataByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(payTempUploadDataService.getTotalByParam(parameter)));            
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
    public Object getRowKey(PayTempUploadData payTempUploadData) {
        return payTempUploadData.getId();
    }

    @Override
    public PayTempUploadData getRowData(String id) {
        for (PayTempUploadData payTempUploadData : list) {
            if (id.equals(String.valueOf(payTempUploadData.getId()))) {
                return payTempUploadData;
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
