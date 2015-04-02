/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BatchJobExecution;
import com.inkubator.hrm.service.BatchJobExecutionService;
import com.inkubator.hrm.web.search.BatchJobExecutionSearchParameter;
import org.apache.log4j.Logger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EKA
 */
public class BatchJobExecutionLazyDataModel extends LazyDataModel<BatchJobExecution> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BatchJobExecution.class);
    private final BatchJobExecutionService batchJobExecutionService;
    private final BatchJobExecutionSearchParameter searchParameter;
    private List<BatchJobExecution> batchJobExecutions = new ArrayList<>();
    private Integer total;
    
    public BatchJobExecutionLazyDataModel(BatchJobExecutionSearchParameter searchParameter, BatchJobExecutionService batchJobExecutionService){
        this.searchParameter = searchParameter;
        this.batchJobExecutionService = batchJobExecutionService;
    }
    
    @Override
    public List<BatchJobExecution> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        LOGGER.info("Step Load Lazy data Model");
                
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                	batchJobExecutions = batchJobExecutionService.getByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    total = Integer.parseInt(String.valueOf(batchJobExecutionService.getTotalByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                	batchJobExecutions = batchJobExecutionService.getByParam(searchParameter, first, pageSize, Order.desc(sortField));
                	total = Integer.parseInt(String.valueOf(batchJobExecutionService.getTotalByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
            	batchJobExecutions = batchJobExecutionService.getByParam(searchParameter, first, pageSize, Order.asc("jobExecutionId"));
                total = Integer.parseInt(String.valueOf(batchJobExecutionService.getTotalByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy Data Model");
            
            setPageSize(pageSize);
            setRowCount(total);
            return batchJobExecutions;
    }
    
    @Override
    public Object getRowKey(BatchJobExecution batchJobExecution){
        return batchJobExecution.getJobExecutionId();
    }
    
    @Override
    public BatchJobExecution getRowData(String jobExecutionId){
        for (BatchJobExecution batchJobExecution : batchJobExecutions){
            if (jobExecutionId.equals(String.valueOf(batchJobExecution.getJobExecutionId()))){
                return batchJobExecution;
            }
        }
        return null;
    }
    
    @Override
    public void setRowIndex(int rowIndex){
        if (rowIndex == -1 || getPageSize() == 0){
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}