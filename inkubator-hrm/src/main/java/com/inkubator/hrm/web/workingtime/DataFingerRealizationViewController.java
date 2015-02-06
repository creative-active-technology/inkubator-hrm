package com.inkubator.hrm.web.workingtime;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.DataFingerRealizationLazyDataModel;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "dataFingerRealizationViewController")
@ViewScoped
public class DataFingerRealizationViewController extends BaseController {

	private WtPeriode periode;
    private DataFingerRealizationSearchParameter searchParameter;
    private LazyDataModel<DataFingerRealizationModel> lazyData;
    private DataFingerRealizationModel selectedModel;
    private JobExecution jobExecution;
    private Integer progress;
    @ManagedProperty(value = "#{tempProcessReadFingerService}")
    private TempProcessReadFingerService tempProcessReadFingerService;
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobSynchDataFingerRealization}")
    private Job jobSynchDataFingerRealization;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
        	periode = wtPeriodeService.getEntityByAbsentTypeActive();
	        searchParameter = new DataFingerRealizationSearchParameter();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	searchParameter = null;
    	lazyData = null;
    	selectedModel = null;
    	tempProcessReadFingerService = null;
    	jobLauncherAsync = null;
    	jobSynchDataFingerRealization = null;
    	jobExecution = null;
    	empDataService = null;
    	progress = null;
    	periode = null;
    	wtPeriodeService = null;
    }
    
	public DataFingerRealizationSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			DataFingerRealizationSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public DataFingerRealizationModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(DataFingerRealizationModel selectedModel) {
		this.selectedModel = selectedModel;
	}

	public TempProcessReadFingerService getTempProcessReadFingerService() {
		return tempProcessReadFingerService;
	}

	public void setTempProcessReadFingerService(
			TempProcessReadFingerService tempProcessReadFingerService) {
		this.tempProcessReadFingerService = tempProcessReadFingerService;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public JobLauncher getJobLauncherAsync() {
		return jobLauncherAsync;
	}

	public void setJobLauncherAsync(JobLauncher jobLauncherAsync) {
		this.jobLauncherAsync = jobLauncherAsync;
	}

	public Job getJobSynchDataFingerRealization() {
		return jobSynchDataFingerRealization;
	}

	public void setJobSynchDataFingerRealization(Job jobSynchDataFingerRealization) {
		this.jobSynchDataFingerRealization = jobSynchDataFingerRealization;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public WtPeriode getPeriode() {
		return periode;
	}

	public void setPeriode(WtPeriode periode) {
		this.periode = periode;
	}

	public LazyDataModel<DataFingerRealizationModel> getLazyData() {
		if(lazyData == null){
			lazyData = new DataFingerRealizationLazyDataModel(searchParameter, tempProcessReadFingerService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<DataFingerRealizationModel> lazyData) {
		this.lazyData = lazyData;
	}	

	public void doSearch() {
        lazyData = null;
    }
	
	public void doSyncDataFinger(){
		/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {	        	
	            long sleepVariable = empDataService.getTotalEmpDataNotTerminate();    	
	            jobExecution = jobLauncherAsync.run(jobSynchDataFingerRealization, new JobParametersBuilder()
	            		.addString("createdBy", UserInfoUtil.getUserName())
				        .addDate("createdOn", new Timestamp(new Date().getTime())).toJobParameters());
	            
	            int i = 0;
	            while(true){
	            	if(jobExecution.getStatus() == BatchStatus.STARTED || jobExecution.getStatus() == BatchStatus.STARTING) {
		            	if(i <= 85){
		            		setProgress(i++);
		            	}
		                try {
		                    Thread.sleep(sleepVariable);
		                } catch (InterruptedException e) {}	
	            	} else {
	            		setProgress(100);
	            		break;
	            	}
	            }
	            
	        } catch (Exception ex) {
	            LOGGER.error("Error ", ex);
	        }
    	}
	}
	
	public void onCompleteSyncDataFinger() {
    	if(jobExecution != null) {
	    	setProgress(0);
	    	if(jobExecution.getStatus() == BatchStatus.COMPLETED){	    		
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.information", "dataFinger.sync_data_finger_succesfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    	} else {	    		
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "dataFinger.sync_data_finger_failed",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		FacesContext.getCurrentInstance().validationFailed();
	    	}
	    	jobExecution = null;
    	}
    }
	
	public void doInitSyncDataFinger(){
		progress=0;
    }

    public String doDetail() {
        return "/protected/working_time/data_finger_real_detail.htm?faces-redirect=true&execution=e" + selectedModel.getEmpDataId();
    }
    
}
