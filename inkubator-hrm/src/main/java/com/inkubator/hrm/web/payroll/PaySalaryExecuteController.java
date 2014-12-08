/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.webcore.controller.BaseController;

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
     @ManagedProperty(value = "#{jobLauncher}")
     private JobLauncher jobLauncher;
     @ManagedProperty(value = "#{jobPayEmployeeCalculation}")
     private Job jobPayEmployeeCalculation;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

    }

    @PreDestroy
    public void cleanAndExit() {

    }

    public void calculatePayRoll() {
        try {
        	payTempKalkulasiService.deleteAllData();
        	
        	JobParameters jobParameters = new JobParametersBuilder()
        			.addString("timeInMilis", String.valueOf(System.currentTimeMillis())).toJobParameters();
        	JobExecution jobExecution = jobLauncher.run(jobPayEmployeeCalculation, jobParameters);
	
            //payTempKalkulasiService.calculatePayRoll();
        } catch (Exception ex) {
          LOGGER.error(ex, ex);
        }
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
        this.payTempKalkulasiService = payTempKalkulasiService;
    }

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public Job getJobPayEmployeeCalculation() {
		return jobPayEmployeeCalculation;
	}

	public void setJobPayEmployeeCalculation(Job jobPayEmployeeCalculation) {
		this.jobPayEmployeeCalculation = jobPayEmployeeCalculation;
	}
    
    

    
}
