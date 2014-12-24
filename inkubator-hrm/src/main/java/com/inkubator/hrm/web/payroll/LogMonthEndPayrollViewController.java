package com.inkubator.hrm.web.payroll;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.LogMonthEndPayrollLazyDataModel;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

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
    @ManagedProperty(value = "#{logMonthEndPayrollService}")
    private LogMonthEndPayrollService logMonthEndPayrollService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
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
        	parameter =  new LogMonthEndPayrollSearchParameter();
			parameter.setPeriodeId(periode.getId());
			totalEmployee = logMonthEndPayrollService.getTotalByParam(new LogMonthEndPayrollSearchParameter());
			totalNominal = logMonthEndPayrollService.getTotalTakeHomePayByPeriodeId(periode.getId());
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

	public void doSearch() {
        lazyDataModel = null;
    }

    public void doMonthEndProcess(){
    	JobParameters jobParameters = new JobParametersBuilder()
	        .addString("timeInMilis", String.valueOf(System.currentTimeMillis()))
	        .addDate("periodeStart", periode.getFromPeriode())
	        .addDate("periodeEnd", periode.getUntilPeriode())
	        .addLong("periodeId", periode.getId())
	        .addString("createdBy", UserInfoUtil.getUserName())
	        .addDate("createdOn", new Timestamp(new Date().getTime())).toJobParameters();
    	try {
			jobExecution = jobLauncherAsync.run(jobMonthEndPayroll, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
