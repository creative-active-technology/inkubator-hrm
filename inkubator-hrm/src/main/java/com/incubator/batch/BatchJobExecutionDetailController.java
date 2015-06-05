/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incubator.batch;

import com.inkubator.hrm.entity.BatchJobExecution;
import com.inkubator.hrm.entity.BatchStepExecution;
import com.inkubator.hrm.service.BatchJobExecutionService;
import com.inkubator.hrm.service.BatchStepExecutionService;
import com.inkubator.hrm.web.lazymodel.BatchJobExecutionLazyDataModel;
import com.inkubator.hrm.web.lazymodel.BatchStepExecutionLazyDataModel;
import com.inkubator.hrm.web.search.BatchJobExecutionSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author EKA
 */
@ManagedBean(name = "batchJobExecutionDetailController")
@ViewScoped
public class BatchJobExecutionDetailController extends BaseController{
    
    private BatchJobExecution selectedBatchJobExecution;
    @ManagedProperty(value = "#{batchJobExecutionService}")
    private BatchJobExecutionService batchJobExecutionService;
    @ManagedProperty(value = "#{batchStepExecutionService}")
    private BatchStepExecutionService batchStepExecutionService;
    private BatchJobExecutionSearchParameter batchJobExecutionSearchParameter;
    private String batchJobExecutionId;
    private List<BatchStepExecution> list = new ArrayList<>();
    private LazyDataModel<BatchStepExecution> lazyDataBatchStepExecution;

    @PostConstruct
    @Override
    public void initialization(){
        try{
            super.initialization();
            batchJobExecutionId = FacesUtil.getRequestParameter("execution");
//            list = batchStepExecutionService.getExitMessageByJobId(Long.parseLong(batchJobExecutionId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        selectedBatchJobExecution = null;
        batchJobExecutionService = null;
        batchStepExecutionService = null;
        batchJobExecutionId = null;
        batchJobExecutionSearchParameter = null;
    }
    
    public String doBack(){
        return "/protected/batch/batch_job_execution.htm?faces-redirect=true";
    }

    public BatchJobExecution getSelectedBatchJobExecution() {
        return selectedBatchJobExecution;
    }

    public void setSelectedBatchJobExecution(BatchJobExecution selectedBatchJobExecution) {
        this.selectedBatchJobExecution = selectedBatchJobExecution;
    }

    public BatchJobExecutionService getBatchJobExecutionService() {
        return batchJobExecutionService;
    }

    public void setBatchJobExecutionService(BatchJobExecutionService batchJobExecutionService) {
        this.batchJobExecutionService = batchJobExecutionService;
    }

    public String getBatchJobExecutionId() {
        return batchJobExecutionId;
    }

    public void setBatchJobExecutionId(String batchJobExecutionId) {
        this.batchJobExecutionId = batchJobExecutionId;
    }

    public List<BatchStepExecution> getList() throws Exception {
        return list;
    }

    public void setList(List<BatchStepExecution> list) {
        this.list = list;
    }

    public LazyDataModel<BatchStepExecution> getLazyDataBatchStepExecution() {
        Long batchJobExecutionId = Long.parseLong(this.batchJobExecutionId.substring(1));
        if(lazyDataBatchStepExecution == null){
            lazyDataBatchStepExecution = new BatchStepExecutionLazyDataModel(batchStepExecutionService, batchJobExecutionId);
        }
        return lazyDataBatchStepExecution;
    }

    public void setLazyDataBatchStepExecution(LazyDataModel<BatchStepExecution> lazyDataBatchStepExecution) {
        this.lazyDataBatchStepExecution = lazyDataBatchStepExecution;
    }
    
    public BatchStepExecutionService getBatchStepExecutionService() {
        return batchStepExecutionService;
    }

    public void setBatchStepExecutionService(BatchStepExecutionService batchStepExecutionService) {
        this.batchStepExecutionService = batchStepExecutionService;
    }

    public BatchJobExecutionSearchParameter getBatchJobExecutionSearchParameter() {
        return batchJobExecutionSearchParameter;
    }

    public void setBatchJobExecutionSearchParameter(BatchJobExecutionSearchParameter batchJobExecutionSearchParameter) {
        this.batchJobExecutionSearchParameter = batchJobExecutionSearchParameter;
    }
}
