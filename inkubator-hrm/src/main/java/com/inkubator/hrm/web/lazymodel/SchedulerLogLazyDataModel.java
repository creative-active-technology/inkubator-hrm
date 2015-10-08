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

import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.service.SchedulerLogService;
import com.inkubator.hrm.web.search.SchedulerLogSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class SchedulerLogLazyDataModel extends LazyDataModel<SchedulerLog> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SchedulerLogLazyDataModel.class);
    private final SchedulerLogSearchParameter searchParameter;
    private final SchedulerLogService service;
    private List<SchedulerLog> schedulerLogList = new ArrayList<>();
    private Integer total;
    
    public SchedulerLogLazyDataModel(SchedulerLogSearchParameter searchParameter, SchedulerLogService service){
    	this.searchParameter = searchParameter;
    	this.service = service;
    }
    
    @Override
    public List<SchedulerLog> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    	LOGGER.info("Step Load Lazy data Model");
    	
    	try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.desc("startExecution");
            }
            schedulerLogList = service.getByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    
    LOGGER.info("Success Load Lazy data Model");

    setPageSize(pageSize);
    setRowCount(total);
    return schedulerLogList;
    }
    
    @Override
    public Object getRowKey(SchedulerLog schedulerLog) {
        return schedulerLog.getId();
    }

    @Override
    public SchedulerLog getRowData(String id) {
        for (SchedulerLog schedulerLog : schedulerLogList) {
            if (id.equals(String.valueOf(schedulerLog.getId()))) {
                return schedulerLog;
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
