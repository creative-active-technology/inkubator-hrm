/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.web.payroll.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.LogWtAttendanceRealizationVmLazyDataModel;
import com.inkubator.hrm.web.lazymodel.PaySalaryExecuteLazyDataModel;
import com.inkubator.hrm.web.lazymodel.TempAttendanceRealizationVmLazyDataModel;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.WtPeriodEmpViewModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "wtPeriodEmpDetailController")
@ViewScoped
public class WtPeriodEmpDetailController extends BaseController {

    
    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;     
    @ManagedProperty(value = "#{logWtAttendanceRealizationService}")
    private LogWtAttendanceRealizationService logWtAttendanceRealizationService;  
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobTempAttendanceRealizationCalculation}")
    private Job jobTempAttendanceRealizationCalculation;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    
    private LazyDataModel<TempAttendanceRealizationViewModel> lazyDataModel;
    private TempAttendanceRealizationViewModel selected;
    private WtPeriodEmpViewModel model;
    private Integer progress;
    private Date payrollCalculationDate;
    private JobExecution jobExecution;
    private WtPeriode wtPeriodePayroll;
    private WtPeriode wtPeriodeAbsen;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();        
        progress = null;        
       
        try {
            //Get selected periodeId
            Long periodeId = Long.valueOf(FacesUtil.getRequestParameter("execution").substring(1));           
            model = wtPeriodeService.getWtPeriodEmpByWtPeriodId(periodeId);          
            SimpleDateFormat  dateFormat = new SimpleDateFormat("MMMM yyyy");
            
            wtPeriodePayroll = wtPeriodeService.getEntityByPayrollTypeActive();
            wtPeriodeAbsen = wtPeriodeService.getEntityByAbsentTypeActive();
            
            if (wtPeriodePayroll != null) {
//                payTempKalkulasiModel.setStartDate(wtPeriodePayroll.getFromPeriode());
//                payTempKalkulasiModel.setEndDate(wtPeriodePayroll.getUntilPeriode());
            }
        } catch (Exception ex) {
            Logger.getLogger(WtPeriodEmpDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        lazyDataModel = null;
        model = null;
        tempAttendanceRealizationService = null;
        wtPeriodeService = null;
        payrollCalculationDate = null;
        jobLauncherAsync = null;
        jobExecution = null;
        wtPeriodePayroll = null;
        wtPeriodeAbsen = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }



    public void doCalculateAttendanceRealization() {
    	/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {	        	
	            
	            long sleepVariable = tempAttendanceRealizationService.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(model.getWtPeriodId().longValue())* 3;
	            JobParameters jobParameters = new JobParametersBuilder()	                    
	                    .addDate("periodUntillDate", model.getUntilPeriode())
	                    .addString("createdBy", UserInfoUtil.getUserName())
	                    .addDate("createdOn", new Date())
	                    .addLong("wtPeriodId", model.getWtPeriodId().longValue())
	                    .toJobParameters();	                    
	            jobExecution = jobLauncherAsync.run(jobTempAttendanceRealizationCalculation, jobParameters);
	            
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
    
    public void onCompleteCalculateAttendanceRealization() {
    	if(jobExecution != null) {
	    	setProgress(0);
	    	if(jobExecution.getStatus() == BatchStatus.COMPLETED){
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.information", "workingTime.attendance_realization_calc_process_succesfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    	} else {
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "workingTime.attendance_realization_calc_process_failed",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		FacesContext.getCurrentInstance().validationFailed();
	    	}
	    	jobExecution = null;
    	}
    }
    
    public void doInitCalculateAttendanceRealization(){
    	try {
			if(empDataService.isEmpDataWithNullWtGroupWorkingExist()) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "workingTime.attendance_realization_calc_error_emp_with_null_wt_group_working_found",
			        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				FacesContext.getCurrentInstance().validationFailed();
			}
			progress=0;
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }

    public String doBack() {
        return "/protected/working_time/wt_period_emp_view.htm?faces-redirect=true";
    }

    
    public LazyDataModel<TempAttendanceRealizationViewModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            
            //if period absen status is Active, get from TempAttendanceRealization, otherwise get from LogAttendanceRealization
            if(StringUtils.equals(model.getStatus(), HRMConstant.WT_PERIOD_STATUS_ACTIVE)){
                lazyDataModel = new TempAttendanceRealizationVmLazyDataModel(tempAttendanceRealizationService, model.getWtPeriodId().longValue());
            }else  if(StringUtils.equals(model.getStatus(), HRMConstant.WT_PERIOD_STATUS_VOID)){
                lazyDataModel = new LogWtAttendanceRealizationVmLazyDataModel(logWtAttendanceRealizationService, model.getWtPeriodId().longValue());
            }
            
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<TempAttendanceRealizationViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public TempAttendanceRealizationViewModel getSelected() {
        return selected;
    }

    public void setSelected(TempAttendanceRealizationViewModel selected) {
        this.selected = selected;
    }

    public void setTempAttendanceRealizationService(TempAttendanceRealizationService tempAttendanceRealizationService) {
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

   

    public JobLauncher getJobLauncherAsync() {
		return jobLauncherAsync;
	}

	public void setJobLauncherAsync(JobLauncher jobLauncherAsync) {
		this.jobLauncherAsync = jobLauncherAsync;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	
    public Job getJobTempAttendanceRealizationCalculation() {
		return jobTempAttendanceRealizationCalculation;
	}

	public void setJobTempAttendanceRealizationCalculation(Job jobTempAttendanceRealizationCalculation) {
		this.jobTempAttendanceRealizationCalculation = jobTempAttendanceRealizationCalculation;
	}

	public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public Date getPayrollCalculationDate() {
		return payrollCalculationDate;
	}

	public void setPayrollCalculationDate(Date payrollCalculationDate) {
		this.payrollCalculationDate = payrollCalculationDate;
	}

	public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

	public WtPeriode getWtPeriodePayroll() {
		return wtPeriodePayroll;
	}

	public void setWtPeriodePayroll(WtPeriode wtPeriodePayroll) {
		this.wtPeriodePayroll = wtPeriodePayroll;
	}

	public WtPeriode getWtPeriodeAbsen() {
		return wtPeriodeAbsen;
	}

	public void setWtPeriodeAbsen(WtPeriode wtPeriodeAbsen) {
		this.wtPeriodeAbsen = wtPeriodeAbsen;
	}

    public WtPeriodEmpViewModel getModel() {
        return model;
    }

    public void setModel(WtPeriodEmpViewModel model) {
        this.model = model;
    }       

	public void setLogWtAttendanceRealizationService(
			LogWtAttendanceRealizationService logWtAttendanceRealizationService) {
		this.logWtAttendanceRealizationService = logWtAttendanceRealizationService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}
        
        
    
}
