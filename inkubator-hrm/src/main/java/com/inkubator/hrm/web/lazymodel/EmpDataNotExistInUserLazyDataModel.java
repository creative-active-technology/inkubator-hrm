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

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;

/**
 *
 * @author rizkykojek
 */
public class EmpDataNotExistInUserLazyDataModel extends LazyDataModel<EmpData> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmpDataNotExistInUserLazyDataModel.class);
    private final String param;
    private final EmpDataService empDataService;
    private List<EmpData> empDatas = new ArrayList<>();
    private Integer total;

    public EmpDataNotExistInUserLazyDataModel(String param, EmpDataService empDataService) {
        this.param = param;
        this.empDataService = empDataService;
    }

    @Override
    public List<EmpData> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("nik");
	        }
	        
	        empDatas = empDataService.getAllDataNotExistInUserByParam(param, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(empDataService.getTotalNotExistInUserByParam(param)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        
        setPageSize(pageSize);
        setRowCount(total);
        return empDatas;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : empDatas) {
            if (id.equals(String.valueOf(empData.getId()))) {
                return empData;
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
