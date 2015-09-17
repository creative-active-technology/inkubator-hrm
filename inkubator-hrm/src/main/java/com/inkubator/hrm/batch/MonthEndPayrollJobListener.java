package com.inkubator.hrm.batch;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.LogMonthEndTaxesService;
import com.inkubator.hrm.service.LogSalaryJournalService;
import com.inkubator.hrm.service.WtPeriodeService;

/**
*
* @author rizkykojek
*/
public class MonthEndPayrollJobListener implements JobExecutionListener {

	private transient Logger LOGGER = Logger.getLogger(getClass());
	private WtPeriodeService wtPeriodeService;
	private LogMonthEndPayrollService logMonthEndPayrollService;
	private LogListOfTransferService logListOfTransferService;
	private LogSalaryJournalService logSalaryJournalService;
	private LogMonthEndTaxesService logMonthEndTaxesService;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {		
		/** delete all record in that period (if any) **/
		Boolean isSuccess =  this.deleteAllRecordInPayrollPeriod();
		if(!isSuccess){
			//jika ada error, canceled the jobs
			jobExecution.stop(); 
		}	
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			try {			
				/** all process after month end, ex: delete temporary table or update period date or etc
				 *  should be in one method, in case of rollback purpose */
				logMonthEndPayrollService.afterMonthEndProcess();
				
			} catch (Exception e) {
				/** delete all the record (case if interruption in the middle of process) */
				this.deleteAllRecordInPayrollPeriod();
				
				/** log the Error Message into BatchLog */
				StringWriter errorMessage = new StringWriter();
				e.printStackTrace(new PrintWriter(errorMessage));
				
				ExitStatus exitStatus = new ExitStatus(BatchStatus.FAILED.toString(), errorMessage.toString());
				jobExecution.setExitStatus(exitStatus);
				jobExecution.setStatus(BatchStatus.FAILED);
			}
			
		} else {
			/** delete all the record (case if interruption in the middle of process) */
			this.deleteAllRecordInPayrollPeriod();
		}

	}
	
	private Boolean deleteAllRecordInPayrollPeriod(){
		
	
		Boolean isSuccess = Boolean.TRUE;
		
		try {			
			WtPeriode periode = wtPeriodeService.getEntityByPayrollTypeActive();			
			
			logMonthEndPayrollService.deleteByPeriodId(periode.getId());
			logListOfTransferService.deleteByPeriodId(periode.getId());
			logSalaryJournalService.deleteByPeriodId(periode.getId());
			logMonthEndTaxesService.deleteByPeriodId(periode.getId());
			
		} catch (Exception e) {
			isSuccess =  Boolean.FALSE;
			LOGGER.error("Error " + e);
		}
		
		return isSuccess;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public LogMonthEndPayrollService getLogMonthEndPayrollService() {
		return logMonthEndPayrollService;
	}

	public void setLogMonthEndPayrollService(
			LogMonthEndPayrollService logMonthEndPayrollService) {
		this.logMonthEndPayrollService = logMonthEndPayrollService;
	}

	public LogListOfTransferService getLogListOfTransferService() {
		return logListOfTransferService;
	}

	public void setLogListOfTransferService(
			LogListOfTransferService logListOfTransferService) {
		this.logListOfTransferService = logListOfTransferService;
	}

	public LogSalaryJournalService getLogSalaryJournalService() {
		return logSalaryJournalService;
	}

	public void setLogSalaryJournalService(
			LogSalaryJournalService logSalaryJournalService) {
		this.logSalaryJournalService = logSalaryJournalService;
	}

	public LogMonthEndTaxesService getLogMonthEndTaxesService() {
		return logMonthEndTaxesService;
	}

	public void setLogMonthEndTaxesService(
			LogMonthEndTaxesService logMonthEndTaxesService) {
		this.logMonthEndTaxesService = logMonthEndTaxesService;
	}	

}
