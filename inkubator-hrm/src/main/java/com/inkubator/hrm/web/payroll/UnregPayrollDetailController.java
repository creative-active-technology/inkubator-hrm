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
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.LogUnregPayrollService;
import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.lazymodel.LogUnregPayrollLazyDataModel;
import com.inkubator.hrm.web.model.UnregPayrollViewModel;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "unregPayrollDetailController")
@ViewScoped
public class UnregPayrollDetailController extends BaseController {

	private UnregSalary unregSalary;
	private UnregPayrollViewModel unregPayrollViewModel;
	private Long totalEmployee;
	private BigDecimal totalNominal;
    private UnregPayrollSearchParameter parameter;
    private JobExecution jobExecution;
    private LazyDataModel<UnregPayrollViewModel> lazyDataModel;
    private Integer progress;
    @ManagedProperty(value = "#{logUnregPayrollService}")
    private LogUnregPayrollService logUnregPayrollService;
    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{tempUnregPayrollService}")
    private TempUnregPayrollService tempUnregPayrollService;
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobUnregPayroll}")
    private Job jobUnregPayroll;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
        try {
        	String id = FacesUtil.getRequestParameter("execution");	
        	unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(id.substring(1)));
        	parameter = new UnregPayrollSearchParameter();
			parameter.setUnregSalaryId(unregSalary.getId());
			totalEmployee = logUnregPayrollService.getTotalEmployeeByUnregSalaryId(unregSalary.getId());
			totalNominal = (totalEmployee == 0 ? new BigDecimal(0) : logUnregPayrollService.getTotalTakeHomePayByUnregSalaryId(unregSalary.getId()));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	logUnregPayrollService = null;
        parameter = null;
        lazyDataModel = null;
        unregSalary = null;
        totalEmployee = null;
        totalNominal = null;
        unregSalaryService = null;
        jobLauncherAsync = null;
        jobUnregPayroll = null;
        jobExecution = null;
        progress = null;
        tempUnregPayrollService = null;
    }

    public String doBack(){
    	return "/protected/payroll/unreg_payroll_view.htm?faces-redirect=true";
	}
    
	public void doSearch() {
        lazyDataModel = null;
    }
	
	public String doDetail(){
		return "/protected/payroll/unreg_payroll_component_detail.htm?faces-redirect=true&execution=e"+unregPayrollViewModel.getEmpDataId()+"&unreg=e"+unregSalary.getId();
	}

    public void doPaymentProcess(){
    	/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {
	        	
	            long sleepVariable = tempUnregPayrollService.getTotalByUnregSalaryId(unregSalary.getId());
	            
	            JobParameters jobParameters = new JobParametersBuilder()
				        .addLong("unregSalaryId", unregSalary.getId())
				        .addString("createdBy", UserInfoUtil.getUserName())
				        .addDate("createdOn", new Timestamp(new Date().getTime())).toJobParameters();
    	
	            jobExecution = jobLauncherAsync.run(jobUnregPayroll, jobParameters);
	            
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
    
    public void onCompletePaymentProcess() {
    	if(jobExecution != null) {
	    	setProgress(0);
	    	if(jobExecution.getStatus() == BatchStatus.COMPLETED){	    		
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.information", "unregpayroll.payment_process_succesfully",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		try {
	    			unregSalary = unregSalaryService.getEntiyByPK(unregSalary.getId());
	    			parameter = new UnregPayrollSearchParameter();
	    			parameter.setUnregSalaryId(unregSalary.getId());
	    			totalEmployee = logUnregPayrollService.getTotalEmployeeByUnregSalaryId(unregSalary.getId());
	    			totalNominal = (totalEmployee == 0 ? new BigDecimal(0) : logUnregPayrollService.getTotalTakeHomePayByUnregSalaryId(unregSalary.getId()));					
		    	} catch (Exception ex) {
		            LOGGER.error("Error ", ex);
		        }
	    	} else {
	    		final List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
                for (final Throwable throwable : exceptions) {                	
                	if (throwable instanceof BussinessException) {
                		BussinessException bussinessException = (BussinessException) throwable;
                		ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), bussinessException.getMessage());
                        FacesUtil.getFacesContext().addMessage(null, msg);                		
                    
                	}else if (throwable.getCause() instanceof BussinessException) {
                		BussinessException bussinessException = (BussinessException) throwable.getCause();
                		ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), bussinessException.getMessage());
                        FacesUtil.getFacesContext().addMessage(null, msg); 
                    }                	
                }
                
	    		MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "unregpayroll.payment_process_failed",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
	    		FacesContext.getCurrentInstance().validationFailed();
	    	}
	    	jobExecution = null;
    	}
    }
    
    public void doInitPaymentProcess(){
    	try {
    		if(unregSalary.getIsAlreadyPaid()) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "unregpayroll.system_has_already_do_the_payment_process",
				        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
					FacesContext.getCurrentInstance().validationFailed();
			} else if(tempUnregPayrollService.getTotalByUnregSalaryId(unregSalary.getId()) == 0) {
				MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_ERROR, "global.error", "unregpayroll.system_has_not_execute_of_calculation_salary_process",
			        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
				FacesContext.getCurrentInstance().validationFailed();
			} 
			progress=0;
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
    }

	public UnregSalary getUnregSalary() {
		return unregSalary;
	}

	public void setUnregSalary(UnregSalary unregSalary) {
		this.unregSalary = unregSalary;
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

	public UnregPayrollSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(UnregPayrollSearchParameter parameter) {
		this.parameter = parameter;
	}

	public JobExecution getJobExecution() {
		return jobExecution;
	}

	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	public LazyDataModel<UnregPayrollViewModel> getLazyDataModel() {
		if(lazyDataModel == null){
			lazyDataModel = new LogUnregPayrollLazyDataModel(parameter, logUnregPayrollService);
		}
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<UnregPayrollViewModel> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public LogUnregPayrollService getLogUnregPayrollService() {
		return logUnregPayrollService;
	}

	public void setLogUnregPayrollService(
			LogUnregPayrollService logUnregPayrollService) {
		this.logUnregPayrollService = logUnregPayrollService;
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

	public Job getJobUnregPayroll() {
		return jobUnregPayroll;
	}

	public void setJobUnregPayroll(Job jobUnregPayroll) {
		this.jobUnregPayroll = jobUnregPayroll;
	}

	public UnregPayrollViewModel getUnregPayrollViewModel() {
		return unregPayrollViewModel;
	}

	public void setUnregPayrollViewModel(UnregPayrollViewModel unregPayrollViewModel) {
		this.unregPayrollViewModel = unregPayrollViewModel;
	}  
    
}
