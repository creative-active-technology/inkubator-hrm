package com.inkubator.hrm.batch;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.service.LogWtProcessReadFingerService;
import com.inkubator.hrm.service.WtPeriodeService;

/**
*
* @author rizkykojek
*/
public class MonthEndAttendanceJobListener implements JobExecutionListener {

	private transient Logger LOGGER = Logger.getLogger(getClass());
	private WtPeriodeService wtPeriodeService;
	private LogWtAttendanceRealizationService logWtAttendanceRealizationService;
	private LogWtProcessReadFingerService logWtProcessReadFingerService;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {		
		try {
			WtPeriode periode = wtPeriodeService.getEntityByAbsentTypeActive();
			
			/** delete all record in that period (if any)**/
			logWtAttendanceRealizationService.deleteByPeriodId(periode.getId());
			logWtProcessReadFingerService.deleteByPeriodId(periode.getId());
			
		} catch (Exception e) {
			jobExecution.stop(); //jika ada error, canceled the jobs
			LOGGER.error("Error " + e);
		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			try {			
				/** all process after month end, ex: delete temporary table or update period date or etc
				 *  should be in one method, in case of rollback purpose */
				logWtAttendanceRealizationService.afterMonthEndProcess();
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

	public LogWtAttendanceRealizationService getLogWtAttendanceRealizationService() {
		return logWtAttendanceRealizationService;
	}

	public void setLogWtAttendanceRealizationService(LogWtAttendanceRealizationService logWtAttendanceRealizationService) {
		this.logWtAttendanceRealizationService = logWtAttendanceRealizationService;
	}

	public LogWtProcessReadFingerService getLogWtProcessReadFingerService() {
		return logWtProcessReadFingerService;
	}

	public void setLogWtProcessReadFingerService(LogWtProcessReadFingerService logWtProcessReadFingerService) {
		this.logWtProcessReadFingerService = logWtProcessReadFingerService;
	}
	
}
