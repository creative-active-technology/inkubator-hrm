package com.inkubator.hrm.web.payroll;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.LogMonthEndPayrollLazyDataModel;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "logMonthEndPayrollViewController")
@ViewScoped
public class LogMonthEndPayrollViewController extends BaseController {

	private WtPeriode periode;
	private Long totalEmployee;
	private BigDecimal totalNominal;
    private LogMonthEndPayrollSearchParameter parameter;
    private JobExecution jobExecution;
    private LazyDataModel<LogMonthEndPayrollViewModel> lazyDataModel;
    private Integer progress;
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobMonthEndPayroll}")
    private Job jobMonthEndPayroll;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
        try {
        	periode = wtPeriodeService.getEntityByPayrollTypeActive();
        	parameter = new LogMonthEndPayrollSearchParameter();
			parameter.setPeriodeId(periode.getId());
			totalEmployee = logMonthEndPayrollService.getTotalByParam(parameter);
			totalNominal = (totalEmployee == 0 ? new BigDecimal(0) : logMonthEndPayrollService.getTotalTakeHomePayByPeriodeId(periode.getId()));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	logMonthEndPayrollService = null;
        parameter = null;
        lazyDataModel = null;
        periode = null;
        totalEmployee = null;
        totalNominal = null;
        wtPeriodeService = null;
        jobLauncherAsync = null;
        jobMonthEndPayroll = null;
        jobExecution = null;
        progress = null;
        payTempKalkulasiService = null;
    }
    
	public LazyDataModel<LogMonthEndPayrollViewModel> getLazyDataModel() {
    	if(lazyDataModel == null) {
    		lazyDataModel = new LogMonthEndPayrollLazyDataModel(parameter, logMonthEndPayrollService);
    	}
		return lazyDataModel;
	}

	public LogMonthEndPayrollSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(LogMonthEndPayrollSearchParameter parameter) {
		this.parameter = parameter;
	}

	public void setLazyDataModel(LazyDataModel<LogMonthEndPayrollViewModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public void setLogMonthEndPayrollService(
			LogMonthEndPayrollService logMonthEndPayrollService) {
		this.logMonthEndPayrollService = logMonthEndPayrollService;
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

	public BigDecimal getTotalNominal() {
		return totalNominal;
	}

	public void setTotalNominal(BigDecimal totalNominal) {
		this.totalNominal = totalNominal;
	}

	public LogMonthEndPayrollService getLogMonthEndPayrollService() {
		return logMonthEndPayrollService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	public JobLauncher getJobLauncherAsync() {
		return jobLauncherAsync;
	}

	public void setJobLauncherAsync(JobLauncher jobLauncherAsync) {
		this.jobLauncherAsync = jobLauncherAsync;
	}

	public Job getJobMonthEndPayroll() {
		return jobMonthEndPayroll;
	}

	public void setJobMonthEndPayroll(Job jobMonthEndPayroll) {
		this.jobMonthEndPayroll = jobMonthEndPayroll;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public PayTempKalkulasiService getPayTempKalkulasiService() {
		return payTempKalkulasiService;
	}

	public void setPayTempKalkulasiService(
			PayTempKalkulasiService payTempKalkulasiService) {
		this.payTempKalkulasiService = payTempKalkulasiService;
	}

	public void doSearch() {
        lazyDataModel = null;
    }

    public void doMonthEndProcess(){
    	/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {
	        	
	            long sleepVariable = payTempKalkulasiService.getTotalData();
	            
	            JobParameters jobParameters = new JobParametersBuilder()
				        .addDate("periodeStart", periode.getFromPeriode())
				        .addDate("periodeEnd", periode.getUntilPeriode())
				        .addLong("periodeId", periode.getId())
				        .addString("createdBy", UserInfoUtil.getUserName())
				        .addDate("createdOn", new Timestamp(new Date().getTime()))
				        .addString("locale", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()).toJobParameters();
    	
	            jobExecution = jobLauncherAsync.run(jobMonthEndPayroll, jobParameters);
	            
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
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.information", "payroll.month_end_process_succesfully",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		try {
	    			parameter = new LogMonthEndPayrollSearchParameter();
	    			parameter.setPeriodeId(periode.getId());
			    	totalEmployee = logMonthEndPayrollService.getTotalByParam(parameter);
					totalNominal = (totalEmployee == 0 ? new BigDecimal(0) : logMonthEndPayrollService.getTotalTakeHomePayByPeriodeId(periode.getId()));					
		    	} catch (Exception ex) {
		            LOGGER.error("Error ", ex);
		        }
	    	} else {
	    		final List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
                for (final Throwable throwable : exceptions) {                	
                	if (throwable instanceof BussinessException) {
                		BussinessException bussinessException = (BussinessException) throwable;
                		ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), bussinessException.getMessage());
                        FacesUtil.getFacesContext().addMessage(null, msg);                		
                    
                	}else if (throwable.getCause() instanceof BussinessException) {
                		BussinessException bussinessException = (BussinessException) throwable.getCause();
                		ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), bussinessException.getMessage());
                        FacesUtil.getFacesContext().addMessage(null, msg); 
                    }                	
                }
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "payroll.month_end_process_failed",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		FacesContext.getCurrentInstance().validationFailed();
	    	}
	    	jobExecution = null;
    	}
    }
    
    public void doInitMonthEndProcess(){
    	try {
			if(payTempKalkulasiService.getTotalData() == 0) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "payroll.system_has_not_execute_of_calculation_salary_process",
			        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				FacesContext.getCurrentInstance().validationFailed();
			}
			progress=0;
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }
    
    
}
