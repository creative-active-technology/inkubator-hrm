package com.inkubator.hrm.batch;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.service.LogUnregListOfTransferService;
import com.inkubator.hrm.service.LogUnregPayrollService;
import com.inkubator.hrm.service.LogUnregTaxesService;

/**
*
* @author rizkykojek
*/
public class UnregPayrollJobListener implements JobExecutionListener {

	private transient Logger LOGGER = Logger.getLogger(getClass());
	private LogUnregPayrollService logUnregPayrollService;
	private LogUnregTaxesService logUnregTaxesService;
	private LogUnregListOfTransferService logUnregListOfTransferService;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {		
		try {				
			/** delete all record (if any)**/
			Boolean isSuccess = this.deleteAllRecordByUnregSalaryId(jobExecution);
			if(!isSuccess){
				//jika ada error, canceled the jobs
				jobExecution.stop(); 
			}
			
		} catch (Exception e) {
			jobExecution.stop(); //jika ada error, canceled the jobs
			LOGGER.error("Error " + e);
		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			try {			
				/** all process after month end, ex: delete temporary table or update payment date or etc
				 *  should be in one method, in case of rollback purpose */
				Long unregSalaryId = jobExecution.getJobParameters().getLong("unregSalaryId");
				logUnregPayrollService.afterPayrollProcess(unregSalaryId);
				
			} catch (Exception e) {
				/** delete all the record (case if interruption in the middle of process) */
				this.deleteAllRecordByUnregSalaryId(jobExecution);
				
				/** log the Error Message into BatchLog */
				StringWriter errorMessage = new StringWriter();
				e.printStackTrace(new PrintWriter(errorMessage));
				
				ExitStatus exitStatus = new ExitStatus(BatchStatus.FAILED.toString(), errorMessage.toString());
				jobExecution.setExitStatus(exitStatus);
				jobExecution.setStatus(BatchStatus.FAILED);
			}
			
		} else {
			/** delete all the record (case if interruption in the middle of process) */
			this.deleteAllRecordByUnregSalaryId(jobExecution);
		}
	}
	
	private Boolean deleteAllRecordByUnregSalaryId(JobExecution jobExecution){		
		Boolean isSuccess = Boolean.TRUE;
		
		try {			
			Long unregSalaryId = jobExecution.getJobParameters().getLong("unregSalaryId");
			logUnregPayrollService.deleteByUnregSalaryId(unregSalaryId);
			logUnregTaxesService.deleteByUnregSalaryId(unregSalaryId);
			logUnregListOfTransferService.deleteByUnregSalaryId(unregSalaryId);
			
		} catch (Exception e) {
			isSuccess =  Boolean.FALSE;
			LOGGER.error("Error " + e);
		}
		
		return isSuccess;
	}

	public LogUnregPayrollService getLogUnregPayrollService() {
		return logUnregPayrollService;
	}

	public void setLogUnregPayrollService(
			LogUnregPayrollService logUnregPayrollService) {
		this.logUnregPayrollService = logUnregPayrollService;
	}

	public LogUnregTaxesService getLogUnregTaxesService() {
		return logUnregTaxesService;
	}

	public void setLogUnregTaxesService(LogUnregTaxesService logUnregTaxesService) {
		this.logUnregTaxesService = logUnregTaxesService;
	}

	public LogUnregListOfTransferService getLogUnregListOfTransferService() {
		return logUnregListOfTransferService;
	}

	public void setLogUnregListOfTransferService(
			LogUnregListOfTransferService logUnregListOfTransferService) {
		this.logUnregListOfTransferService = logUnregListOfTransferService;
	}
	
}
