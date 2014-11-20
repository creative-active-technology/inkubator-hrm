/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.service.WtFingerExceptionService;
import com.inkubator.hrm.web.search.WtFingerExceptionSearchParameter;
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
public class WtFingerExceptionLazyDataModel extends LazyDataModel<WtFingerException> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(WtFingerException.class);
    private final WtFingerExceptionSearchParameter searchParameter;
    private final WtFingerExceptionService service;
    private List<WtFingerException> wtFingerExceptionList = new ArrayList<>();
    private Integer jumlahData;

    public WtFingerExceptionLazyDataModel(WtFingerExceptionSearchParameter searchParameter, WtFingerExceptionService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<WtFingerException> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("empData");
	        }
	        
	        wtFingerExceptionList = service.getByParamWithDetail(searchParameter, first, pageSize, orderable);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalWtFingerExceptionByParam(searchParameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return wtFingerExceptionList;
    }
    
    @Override
    public Object getRowKey(WtFingerException wtFingerException) {
        return wtFingerException.getId();
    }

    @Override
    public WtFingerException getRowData(String id) {
        for (WtFingerException wtFingerException : wtFingerExceptionList) {
            if (id.equals(String.valueOf(wtFingerException.getId()))) {
                return wtFingerException;
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
