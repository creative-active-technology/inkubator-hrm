/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.BatchJobExecution;
import com.inkubator.hrm.entity.BatchStepExecution;
import com.inkubator.hrm.service.BatchStepExecutionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author EKA
 */
public class BatchStepExecutionLazyDataModel extends LazyDataModel<BatchStepExecution> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(BatchStepExecutionLazyDataModel.class);
    private final BatchStepExecutionService service;
    private List<BatchStepExecution> messageList = new ArrayList<>();
    private Long jobExecutionId;
    Integer jumlah;
   
    
    public BatchStepExecutionLazyDataModel(BatchStepExecutionService service, Long jobExecutionId){
        this.service = service;
        this.jobExecutionId = jobExecutionId;
    }
    
    @Override
    public List<BatchStepExecution> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
        LOGGER.info("Step Load Lazy Data Model");
        if(sortField != null){
            if(sortOrder == SortOrder.ASCENDING){
                try{
                    messageList = service.getExitMessageByJobId(jobExecutionId);
                    jumlah = Integer.parseInt(String.valueOf(service.getTotalExitMessageById(jobExecutionId)));
                } catch (Exception ex){
                    LOGGER.error("Error", ex);
                }
            } else {
                try{
                    messageList = service.getExitMessageByJobId(jobExecutionId);
                    jumlah =  Integer.parseInt(String.valueOf(service.getTotalExitMessageById(jobExecutionId)));
                } catch (Exception ex){
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try{
                messageList = service.getExitMessageByJobId(jobExecutionId);
                jumlah = Integer.parseInt(String.valueOf(service.getTotalExitMessageById(jobExecutionId)));
            } catch (Exception ex){
                LOGGER.error("Error", ex);
            }
        }
        System.out.println(pageSize + " asdfadf");
        System.out.println(messageList.size() + " asdfadsf");
        System.out.println(jumlah + " asfasdf");
        setPageSize(pageSize);
        setRowCount(jumlah);
        return messageList;
    }
    
    @Override
    public Object getRowKey(BatchStepExecution batchStepExecution){
        return batchStepExecution.getBatchJobExecution().getJobExecutionId();
    }
    
    @Override
    public BatchStepExecution getRowData(String id){
        for (BatchStepExecution message : messageList){
            if(id.equals(String.valueOf(message.getBatchJobExecution().getJobExecutionId())))
                return message;
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
