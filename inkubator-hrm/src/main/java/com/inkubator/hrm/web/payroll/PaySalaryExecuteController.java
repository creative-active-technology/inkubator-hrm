/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

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
import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PaySalaryExecuteLazyDataModel;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryExecuteController")
@ViewScoped
public class PaySalaryExecuteController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{payTempKalkulasiService}")
    private PayTempKalkulasiService payTempKalkulasiService;
    @ManagedProperty(value = "#{payTempKalkulasiEmpPajakService}")
    private PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService;
    private PayTempKalkulasiSearchParameter searchParameter;
    private LazyDataModel<PayTempKalkulasiModel> lazyDataModel;
    private PayTempKalkulasi selected;
    private String parameter;
    private Integer jumlahKaryawan;
    private Double jumlahNominal;
    @ManagedProperty(value = "#{jobLauncherAsync}")
    private JobLauncher jobLauncherAsync;
    @ManagedProperty(value = "#{jobPayEmployeeCalculation}")
    private Job jobPayEmployeeCalculation;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private Long getTotalKaryawan;
    private PayTempKalkulasiModel payTempKalkulasiModel;
    private Integer progress;
    private Date payrollCalculationDate;
    private JobExecution jobExecution;
    private WtPeriode wtPeriodePayroll;
    private WtPeriode wtPeriodeAbsen;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PayTempKalkulasiSearchParameter();
        payTempKalkulasiModel = new PayTempKalkulasiModel();
        progress = null;        
       
        try {
            wtPeriodePayroll = wtPeriodeService.getEntityByPayrollTypeActive();
            wtPeriodeAbsen = wtPeriodeService.getEntityByAbsentTypeActive();
            getTotalKaryawan = empDataService.getTotalEmpDataNotTerminate();
            if (wtPeriodePayroll != null) {
                payTempKalkulasiModel.setStartDate(wtPeriodePayroll.getFromPeriode());
                payTempKalkulasiModel.setEndDate(wtPeriodePayroll.getUntilPeriode());
            }
        } catch (Exception ex) {
            Logger.getLogger(PaySalaryExecuteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {
        selected = null;
        lazyDataModel = null;
        searchParameter = null;
        payTempKalkulasiService = null;
        empDataService = null;
        parameter = null;
        getTotalKaryawan = null;
        payrollCalculationDate = null;
        jobLauncherAsync = null;
        jobExecution = null;
        wtPeriodePayroll = null;
        wtPeriodeAbsen = null;
        payTempKalkulasiEmpPajakService = null;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.payTempKalkulasiService.getEntityByPkWithDetail(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doCalculatePayroll() {
    	/** to cater prevent multiple click, that will make batch execute multiple time. 
    	 *  please see onComplete method that will set jobExecution == null */
    	if(jobExecution == null){ 
	        try {
	        	
	        	payTempKalkulasiService.deleteAllData();
	        	payTempKalkulasiEmpPajakService.deleteAllData();
	            long sleepVariable = empDataService.getTotalEmpDataNotTerminate() * 3;
	            
	            JobParameters jobParameters = new JobParametersBuilder()
	                    .addString("timeInMilis", String.valueOf(System.currentTimeMillis()))
	                    .addDate("payrollCalculationDate", payrollCalculationDate)
		                .addString("createdBy", UserInfoUtil.getUserName()).toJobParameters();
	            jobExecution = jobLauncherAsync.run(jobPayEmployeeCalculation, jobParameters);
	            
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
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informasi","Kalkulasi Penggajian sukses dilakukan"));
	    	} else {
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informasi","Kalkulasi Penggajian gagal dilakukan"));
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
        return "/protected/payroll/salary_execution_detail.htm?faces-redirect=true&execution=e" + payTempKalkulasiModel.getPaySalaryComponentId();
    }

    public String doTax(){
        return "/protected/payroll/tax_view.htm?faces-redirect=true";
    }
    
    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

    public PayTempKalkulasiSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PayTempKalkulasiSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PayTempKalkulasiModel> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PaySalaryExecuteLazyDataModel(parameter, payTempKalkulasiService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayTempKalkulasiModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayTempKalkulasi getSelected() {
        return selected;
    }

    public void setSelected(PayTempKalkulasi selected) {
        this.selected = selected;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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

	public Job getJobPayEmployeeCalculation() {
        return jobPayEmployeeCalculation;
    }

    public void setJobPayEmployeeCalculation(Job jobPayEmployeeCalculation) {
        this.jobPayEmployeeCalculation = jobPayEmployeeCalculation;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public Double getJumlahNominal() {
        return jumlahNominal;
    }

    public void setJumlahNominal(Double jumlahNominal) {
        this.jumlahNominal = jumlahNominal;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public Long getGetTotalKaryawan() {
        return getTotalKaryawan;
    }

    public void setGetTotalKaryawan(Long getTotalKaryawan) {
        this.getTotalKaryawan = getTotalKaryawan;
    }

    public PayTempKalkulasiModel getPayTempKalkulasiModel() {
        return payTempKalkulasiModel;
    }

    public void setPayTempKalkulasiModel(PayTempKalkulasiModel payTempKalkulasiModel) {
        this.payTempKalkulasiModel = payTempKalkulasiModel;
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

	public void setPayTempKalkulasiEmpPajakService(
			PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService) {
		this.payTempKalkulasiEmpPajakService = payTempKalkulasiEmpPajakService;
	}
    
	
    
}
