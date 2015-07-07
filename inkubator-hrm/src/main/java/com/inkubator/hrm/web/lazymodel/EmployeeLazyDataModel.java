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
import com.inkubator.hrm.web.search.EmpDataSearchParameter;

/**
 *
 * @author rizkykojek
 */
public class EmployeeLazyDataModel extends LazyDataModel<EmpData> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmployeeLazyDataModel.class);
    private final EmpDataSearchParameter empDataSearchParameter;
    private final EmpDataService empDataService;
    private List<EmpData> empDatadatas = new ArrayList<>();
    private Integer jumlah;

    public EmployeeLazyDataModel(EmpDataSearchParameter empDataSearchParameter, EmpDataService empDataService) {
        this.empDataSearchParameter = empDataSearchParameter;
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
	        	orderable = Order.asc("nik");
	        }
	        
	        empDatadatas = empDataService.getAllDataByParam(empDataSearchParameter, first, pageSize, orderable);
	        jumlah = Integer.parseInt(String.valueOf(empDataService.getTotalByParam(empDataSearchParameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }
        setPageSize(pageSize);
        setRowCount(jumlah);
        return empDatadatas;
    }

    @Override
    public Object getRowKey(EmpData empData) {
        return empData.getId();
    }

    @Override
    public EmpData getRowData(String id) {
        for (EmpData empData : empDatadatas) {
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
