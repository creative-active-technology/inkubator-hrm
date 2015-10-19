package com.inkubator.hrm.batch;


import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.service.TempAttendanceRealizationService;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class TempAttendanceRealizationCalculationJobListener implements JobExecutionListener {
	
	private transient Logger LOGGER = Logger.getLogger(getClass());	
	private TempAttendanceRealizationService tempAttendanceRealizationService;	
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		try {
			
			/** delete all the record, will be recalculated during batch process **/
			tempAttendanceRealizationService.deleteAllData();			
			
		} catch (Exception e) {
			jobExecution.stop(); //jika ada error, canceled the jobs
			LOGGER.error("Error " + e);
		}
		
	}
	
	

	@Override
	public void afterJob(JobExecution jobExecution) {		
		if (jobExecution.getAllFailureExceptions().isEmpty()) {
			/*try {
				*//** update payroll date di wtPeriode*//*
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
			}*/
		}		
	}

	public TempAttendanceRealizationService getTempAttendanceRealizationService() {
		return tempAttendanceRealizationService;
	}

	public void setTempAttendanceRealizationService(
			TempAttendanceRealizationService tempAttendanceRealizationService) {
		this.tempAttendanceRealizationService = tempAttendanceRealizationService;
	}

	
}
