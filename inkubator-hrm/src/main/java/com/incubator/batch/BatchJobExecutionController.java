/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incubator.batch;

import com.inkubator.hrm.entity.BatchJobExecution;
import com.inkubator.hrm.service.BatchJobExecutionService;
import com.inkubator.hrm.web.lazymodel.BatchJobExecutionLazyDataModel;
import com.inkubator.hrm.web.search.BatchJobExecutionSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author EKA
 */
@ManagedBean(name = "batchJobExecutionController")
@ViewScoped

public class BatchJobExecutionController extends BaseController{
    
    @ManagedProperty(value = "#{batchJobExecutionService}")
    private BatchJobExecutionService batchJobExecutionService;
    private BatchJobExecutionSearchParameter batchJobExecutionSearchParameter;
    private LazyDataModel<BatchJobExecution> lazyDataBatchJobExecution;
    private PieChartModel pieModel;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        batchJobExecutionSearchParameter = new BatchJobExecutionSearchParameter();
    }
    
    @PreDestroy
    public void cleanAndExit(){
        batchJobExecutionService = null;
        batchJobExecutionSearchParameter = null;
        lazyDataBatchJobExecution = null;
        pieModel = null;
    }
    
    public void setBatchJobExecutionService(BatchJobExecutionService batchJobExecutionService){
        this.batchJobExecutionService = batchJobExecutionService;
    }
    
    public BatchJobExecutionSearchParameter getBatchJobExecutionSearchParameter(){
        return batchJobExecutionSearchParameter;
    }
    
    public void setBatchJobExecutionSearchParameter(BatchJobExecutionSearchParameter batchJobExecutionSearchParameter){
        this.batchJobExecutionSearchParameter = batchJobExecutionSearchParameter;
    }

    public LazyDataModel<BatchJobExecution> getLazyDataBatchJobExecution() {
        if(lazyDataBatchJobExecution == null){
            lazyDataBatchJobExecution = new BatchJobExecutionLazyDataModel(batchJobExecutionSearchParameter, batchJobExecutionService);
        }
        return lazyDataBatchJobExecution;
    }

    public void setLazyDataBatchJobExecution(LazyDataModel<BatchJobExecution> lazyDataBatchJobExecution) {
        this.lazyDataBatchJobExecution = lazyDataBatchJobExecution;
    }
    
    
    
    public PieChartModel getPieModel(){
        return pieModel;
    }
    
    public void doSearch(){
        lazyDataBatchJobExecution = null;
    }
}
