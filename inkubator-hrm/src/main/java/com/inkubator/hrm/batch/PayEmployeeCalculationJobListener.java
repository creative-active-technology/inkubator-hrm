package com.inkubator.hrm.batch;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayTempKalkulasiEmpPajakService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.WtPeriodeService;

public class PayEmployeeCalculationJobListener implements JobExecutionListener {
	
	private transient Logger LOGGER = Logger.getLogger(getClass());
	private WtPeriodeService wtPeriodeService;
	private PayTempKalkulasiService payTempKalkulasiService;
	private PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		/** delete all the record, will be recalculated during batch process **/
		Boolean isSuccess =  this.deleteRelatedEntity();
		if(!isSuccess){
			//jika ada error, canceled the jobs
			jobExecution.stop(); 
		}		
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
				/** delete all the record (case if interruption in the middle of process) */
				this.deleteRelatedEntity();
				
				/** log the Error Message into BatchLog */
				StringWriter errorMessage = new StringWriter();
				e.printStackTrace(new PrintWriter(errorMessage));
				
				ExitStatus exitStatus = new ExitStatus(BatchStatus.FAILED.toString(), errorMessage.toString());
				jobExecution.setExitStatus(exitStatus);
				jobExecution.setStatus(BatchStatus.FAILED);
			}
			
		} else {
			/** delete all the record (case if interruption in the middle of process) */
			this.deleteRelatedEntity();
		}
	}
	
	private Boolean deleteRelatedEntity(){
		Boolean isSuccess = Boolean.TRUE;
		
		try {	
			payTempKalkulasiService.deleteAllData();
			payTempKalkulasiEmpPajakService.deleteAllData();
			
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

	public PayTempKalkulasiService getPayTempKalkulasiService() {
		return payTempKalkulasiService;
	}

	public void setPayTempKalkulasiService(
			PayTempKalkulasiService payTempKalkulasiService) {
		this.payTempKalkulasiService = payTempKalkulasiService;
	}

	public PayTempKalkulasiEmpPajakService getPayTempKalkulasiEmpPajakService() {
		return payTempKalkulasiEmpPajakService;
	}

	public void setPayTempKalkulasiEmpPajakService(
			PayTempKalkulasiEmpPajakService payTempKalkulasiEmpPajakService) {
		this.payTempKalkulasiEmpPajakService = payTempKalkulasiEmpPajakService;
	}

}
