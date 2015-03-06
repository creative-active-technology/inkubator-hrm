package com.inkubator.hrm.batch;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.TempUnregPayrollEmpPajakService;
import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.service.UnregSalaryService;

/**
*
* @author rizkykojek
*/
public class UnregCalculationJobListener implements JobExecutionListener {
	
	private transient Logger LOGGER = Logger.getLogger(getClass());
	private TempUnregPayrollService tempUnregPayrollService;
	private TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService;
	private UnregSalaryService unregSalaryService;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		try {
			Long unregSalaryId = jobExecution.getJobParameters().getLong("unregSalaryId");
			/** delete all the record, will be recalculated during batch process **/
			tempUnregPayrollService.deleteByUnregSalaryId(unregSalaryId);
			tempUnregPayrollEmpPajakService.deleteByUnregSalaryId(unregSalaryId);
			
		} catch (Exception e) {
			jobExecution.stop(); //jika ada error, canceled the jobs
			LOGGER.error("Error " + e);
		}
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			try {
				/** update salary date di unregSalary */
				Date salaryDate = jobExecution.getJobParameters().getDate("salaryDate");
				Long unregSalaryId = jobExecution.getJobParameters().getLong("unregSalaryId");
				UnregSalary unregSalary = unregSalaryService.getEntiyByPK(unregSalaryId);
				unregSalary.setSalaryDate(salaryDate);
				unregSalaryService.saveOrUpdate(unregSalary);
				
			} catch (Exception e) {
				StringWriter errorMessage = new StringWriter();
				e.printStackTrace(new PrintWriter(errorMessage));
				
				ExitStatus exitStatus = new ExitStatus(BatchStatus.FAILED.toString(), errorMessage.toString());
				jobExecution.setExitStatus(exitStatus);
				jobExecution.setStatus(BatchStatus.FAILED);
			}
		}
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

	public TempUnregPayrollEmpPajakService getTempUnregPayrollEmpPajakService() {
		return tempUnregPayrollEmpPajakService;
	}

	public void setTempUnregPayrollEmpPajakService(
			TempUnregPayrollEmpPajakService tempUnregPayrollEmpPajakService) {
		this.tempUnregPayrollEmpPajakService = tempUnregPayrollEmpPajakService;
	}
	
	
}
