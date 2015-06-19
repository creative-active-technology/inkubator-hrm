package com.inkubator.hrm.web.workingtime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.TempAttendanceRealizationMonthEndViewModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "monthEndAttendanceViewController")
@ViewScoped
public class MonthEndAttendanceViewController extends BaseController {

	private WtPeriode periode;
	private Long totalEmployee = 0L;
	private Double totalOvertime = 0.0;
	private Double totalAttendance = 0.0;
	private List<TempAttendanceRealizationMonthEndViewModel> listModel;
    private JobExecution jobExecution;
    private Integer progress;
    
    @ManagedProperty(value = "#{tempAttendanceRealizationService}")
    private TempAttendanceRealizationService tempAttendanceRealizationService;
    @ManagedProperty(value = "#{tempProcessReadFingerService}")
    private TempProcessReadFingerService tempProcessReadFingerService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobMonthEndAttendance}")
    private Job jobMonthEndAttendance;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();   
        this.doInitialLoadData();
    }

    @PreDestroy
    public void cleanAndExit() {
    	periode = null;
    	totalEmployee = null;
    	totalOvertime = null;
    	totalAttendance = null;
    	listModel = null;
    	jobExecution = null;
    	progress = null;
    	tempProcessReadFingerService = null;
    	tempAttendanceRealizationService = null;
    	wtPeriodeService = null;
    	empDataService = null;
    	jobLauncherAsync = null;
    	jobMonthEndAttendance = null;
    }
    
    public void doInitialLoadData() {
    	try {
	    	periode = wtPeriodeService.getEntityByAbsentTypeActive();
	    	listModel = tempAttendanceRealizationService.getAllDataMonthEndByPeriodId(periode.getId());
	    	if(!listModel.isEmpty()) {
	        	totalEmployee = Lambda.sum(listModel, Lambda.on(TempAttendanceRealizationMonthEndViewModel.class).getTotalEmployee());
	        	totalOvertime = Lambda.sum(listModel, Lambda.on(TempAttendanceRealizationMonthEndViewModel.class).getOvertime());
	        	totalAttendance = new BigDecimal(Lambda.sum(listModel, Lambda.on(TempAttendanceRealizationMonthEndViewModel.class).getAttendance()))
	        		.divide(new BigDecimal(listModel.size()),2,RoundingMode.HALF_UP).doubleValue();
	    	}
    	} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }
    
    public void doMonthEndProcess(){
    	/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {
	        	
	            long sleepVariable = empDataService.getTotalEmpDataNotTerminate();
	            
	            JobParameters jobParameters = new JobParametersBuilder()
				        .addDate("periodeStart", periode.getFromPeriode())
				        .addDate("periodeEnd", periode.getUntilPeriode())
				        .addLong("periodeId", periode.getId())
				        .addString("createdBy", UserInfoUtil.getUserName())
				        .addDate("createdOn", new Timestamp(new Date().getTime()))
				        .addString("locale", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()).toJobParameters();
    	
	            jobExecution = jobLauncherAsync.run(jobMonthEndAttendance, jobParameters);
	            
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
    
    public void onCompleteMonthEndProcess() {
    	if(jobExecution != null) {
	    	setProgress(0);
	    	if(jobExecution.getStatus() == BatchStatus.COMPLETED){	 
	    		this.doInitialLoadData();
	    		
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.information", "payroll.month_end_process_succesfully",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    	} else {
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "payroll.month_end_process_failed",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		FacesContext.getCurrentInstance().validationFailed();
	    	}
	    	jobExecution = null;
    	}
    }
    
    public void doInitMonthEndProcess(){
    	try {
			if(tempAttendanceRealizationService.getTotalData() == 0 || tempProcessReadFingerService.getTotalData() == 0) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "workingTime.system_has_not_execute_of_finger_or_working_time_realization",
			        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				FacesContext.getCurrentInstance().validationFailed();
			}
			progress=0;
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }
    
    public WtPeriode getPeriode() {
		return periode;
	}

	public void setPeriode(WtPeriode periode) {
		this.periode = periode;
	}

	public Long getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	public Double getTotalOvertime() {
		return totalOvertime;
	}

	public void setTotalOvertime(Double totalOvertime) {
		this.totalOvertime = totalOvertime;
	}

	public Double getTotalAttendance() {
		return totalAttendance;
	}

	public void setTotalAttendance(Double totalAttendance) {
		this.totalAttendance = totalAttendance;
	}

	public List<TempAttendanceRealizationMonthEndViewModel> getListModel() {
		return listModel;
	}

	public void setListModel(
			List<TempAttendanceRealizationMonthEndViewModel> listModel) {
		this.listModel = listModel;
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

	public TempAttendanceRealizationService getTempAttendanceRealizationService() {
		return tempAttendanceRealizationService;
	}

	public void setTempAttendanceRealizationService(
			TempAttendanceRealizationService tempAttendanceRealizationService) {
		this.tempAttendanceRealizationService = tempAttendanceRealizationService;
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

	public Job getJobMonthEndAttendance() {
		return jobMonthEndAttendance;
	}

	public void setJobMonthEndAttendance(Job jobMonthEndAttendance) {
		this.jobMonthEndAttendance = jobMonthEndAttendance;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public TempProcessReadFingerService getTempProcessReadFingerService() {
		return tempProcessReadFingerService;
	}

	public void setTempProcessReadFingerService(TempProcessReadFingerService tempProcessReadFingerService) {
		this.tempProcessReadFingerService = tempProcessReadFingerService;
	}    
	
	
}
