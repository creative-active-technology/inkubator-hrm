/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

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
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.lazymodel.UnregCalculationExecuteLazyDataModel;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregCalculationExecuteController")
@ViewScoped
public class UnregCalculationExecuteController extends BaseController {

    
    private LazyDataModel<UnregSalaryCalculationExecuteModel> lazyDataModel;
    private UnregSalary unregSalary;    
    private UnregSalaryCalculationExecuteModel selectedModel;
    private Integer progress;
    private Date payrollCalculationDate;
    private JobExecution jobExecution;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{tempUnregPayrollService}")
    private TempUnregPayrollService tempUnregPayrollService;
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobUnregCalculation}")
    private Job jobUnregCalculation;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {	        
	        String id = FacesUtil.getRequestParameter("execution");	        
	        unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(id.substring(1)));
	        progress = null; 
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }        
    }

    @PreDestroy
    public void cleanAndExit() {
        unregSalary = null;
        lazyDataModel = null;
        unregSalaryService = null;
        empDataService = null;
        payrollCalculationDate = null;
        jobLauncherAsync = null;
        jobExecution = null;
        selectedModel = null;
        jobUnregCalculation = null;
        progress = null;
    }

    public void doCalculatePayroll() {
    	/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {	        	
	            long sleepVariable = empDataService.getTotalEmpDataNotTerminate() * 3;
	            
	            JobParameters jobParameters = new JobParametersBuilder()	                    
	                    .addDate("salaryDate", payrollCalculationDate)
	                    .addLong("unregSalaryId", unregSalary.getId())
		                .addString("createdBy", UserInfoUtil.getUserName())
		                .addDate("createdOn", new Date()).toJobParameters();
	            jobExecution = jobLauncherAsync.run(jobUnregCalculation, jobParameters);
	            
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
    
    public void onCompleteCalculatePayroll() {
    	if(jobExecution != null) {
	    	setProgress(0);
	    	if(jobExecution.getStatus() == BatchStatus.COMPLETED){
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.information", "salaryCalculation.calculation_process_succesfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		try {
	    			unregSalary = unregSalaryService.getEntiyByPK(unregSalary.getId());
	    		} catch (Exception e) {
	    			LOGGER.error("Error ", e);
	    		}
	    	} else {
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "salaryCalculation.calculation_process_failed",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		FacesContext.getCurrentInstance().validationFailed();
	    	}
	    	jobExecution = null;
    	}
    }
    
    public void doInitCalculatePayroll(){
    	try {
			if(empDataService.getTotalByTaxFreeIsNull()>0) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "salaryCalculation.error_employee_does_not_have_ptkp",
			        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				FacesContext.getCurrentInstance().validationFailed();
			}
			progress=0;
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }

    public String doDetail() {
        return "/protected/payroll/unreg_salary_calc_detail.htm?faces-redirect=true&execution=e" + selectedModel.getUnregSalaryId() + "&comp=e" + selectedModel.getPaySalaryCompId();    	
    }

    public String doTax(){
        return "/protected/payroll/unreg_calculation_tax_view.htm?faces-redirect=true&execution=e" + unregSalary.getId();
    }
    
    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public LazyDataModel<UnregSalaryCalculationExecuteModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new UnregCalculationExecuteLazyDataModel(unregSalary.getId(), tempUnregPayrollService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<UnregSalaryCalculationExecuteModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

	public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

	public UnregSalary getUnregSalary() {
		return unregSalary;
	}

	public void setUnregSalary(UnregSalary unregSalary) {
		this.unregSalary = unregSalary;
	}

	public UnregSalaryCalculationExecuteModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(UnregSalaryCalculationExecuteModel selectedModel) {
		this.selectedModel = selectedModel;
	}

	public Date getPayrollCalculationDate() {
		return payrollCalculationDate;
	}

	public void setPayrollCalculationDate(Date payrollCalculationDate) {
		this.payrollCalculationDate = payrollCalculationDate;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	public UnregSalaryService getUnregSalaryService() {
		return unregSalaryService;
	}

	public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
		this.unregSalaryService = unregSalaryService;
	}

	public TempUnregPayrollService getTempUnregPayrollService() {
		return tempUnregPayrollService;
	}

	public void setTempUnregPayrollService(
			TempUnregPayrollService tempUnregPayrollService) {
		this.tempUnregPayrollService = tempUnregPayrollService;
	}

	public JobLauncher getJobLauncherAsync() {
		return jobLauncherAsync;
	}

	public void setJobLauncherAsync(JobLauncher jobLauncherAsync) {
		this.jobLauncherAsync = jobLauncherAsync;
	}

	public Job getJobUnregCalculation() {
		return jobUnregCalculation;
	}

	public void setJobUnregCalculation(Job jobUnregCalculation) {
		this.jobUnregCalculation = jobUnregCalculation;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}
    
}
