package com.inkubator.hrm.batch;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.WtPeriodeService;

public class PayEmployeeCalculationJobListener implements JobExecutionListener {
	
	private WtPeriodeService wtPeriodeService;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {		
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			try {
				/** update payroll date di wtPeriode*/
				Date payrollDate = jobExecution.getJobParameters().getDate("payrollCalculationDate");
				WtPeriode periode = wtPeriodeService.getEntityByPayrollTypeActive();
				periode.setPayrollDate(payrollDate);
				wtPeriodeService.saveOrUpdate(periode);
				
			} catch (Exception e) {
				StringWriter errorMessage = new StringWriter();
				e.printStackTrace(new PrintWriter(errorMessage));
				
				ExitStatus exitStatus = new ExitStatus(BatchStatus.FAILED.toString(), errorMessage.toString());
				jobExecution.setExitStatus(exitStatus);
				jobExecution.setStatus(BatchStatus.FAILED);
			}
		}		
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

}
