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

import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.SchedulerConfigService;
import com.inkubator.hrm.web.search.SchedulerConfigSearchParameter;

/**
 *
 * @author arsyad_
 */
public class SchedulerConfigLazyDataModel extends LazyDataModel<SchedulerConfig> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SchedulerConfigLazyDataModel.class);
    private final SchedulerConfigSearchParameter searchParameter;
    private final SchedulerConfigService service;
    private List<SchedulerConfig> schedulerConfigList = new ArrayList<>();
    private Integer total;
    
    public SchedulerConfigLazyDataModel(SchedulerConfigSearchParameter searchParameter, SchedulerConfigService service){
    	this.searchParameter = searchParameter;
    	this.service = service;
    }
    
    @Override
    public List<SchedulerConfig> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    	LOGGER.info("Step Load Lazy data Model");
    	
    	try {
            Order order = null;
            if(sortField != null){
                order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
            }else{
                order = Order.desc("name");
            }
            schedulerConfigList = service.getByParam(searchParameter, first, pageSize, order);
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(searchParameter)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    
    LOGGER.info("Success Load Lazy data Model");

    setPageSize(pageSize);
    setRowCount(total);
    return schedulerConfigList;
    }
    
    @Override
    public Object getRowKey(SchedulerConfig schedulerConfig) {
        return schedulerConfig.getId();
    }

    @Override
    public SchedulerConfig getRowData(String id) {
        for (SchedulerConfig schedulerConfig : schedulerConfigList) {
            if (id.equals(String.valueOf(schedulerConfig.getId()))) {
                return schedulerConfig;
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
