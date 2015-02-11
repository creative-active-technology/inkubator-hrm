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
	
	
	@Override
	public void beforeJob(JobExecution jobExecution) {		
		try {
			WtPeriode periode = wtPeriodeService.getEntityByPayrollTypeActive();
			
			/** delete all record in that period (if any)**/
			logMonthEndPayrollService.deleteByPeriodId(periode.getId());
			logListOfTransferService.deleteByPeriodId(periode.getId());
			logSalaryJournalService.deleteByPeriodId(periode.getId());
			
		} catch (Exception e) {
			jobExecution.stop(); //jika ada error, canceled the jobs
			LOGGER.error("Error " + e);
		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			try {			
				logMonthEndPayrollService.afterMonthEndProcess();
				
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

}
