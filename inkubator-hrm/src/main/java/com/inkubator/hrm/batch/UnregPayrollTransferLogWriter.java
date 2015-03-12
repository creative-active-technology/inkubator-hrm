package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.service.LogUnregListOfTransferService;

/**
*
* @author rizkykojek
*/
public class UnregPayrollTransferLogWriter implements ItemWriter<TempUnregPayroll> {

	private LogUnregListOfTransferService logUnregListOfTransferService;
	private String createdBy;
	private Date createdOn;
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) throws Exception {
		
	}
	
	@Override
	public void write(List<? extends TempUnregPayroll> items) throws Exception {
		for(TempUnregPayroll tempUnregPayroll : items){
			logUnregListOfTransferService.executeBatchUnregPayroll(tempUnregPayroll, createdBy, createdOn);
		}		
	}

	public LogUnregListOfTransferService getLogUnregListOfTransferService() {
		return logUnregListOfTransferService;
	}

	public void setLogUnregListOfTransferService(
			LogUnregListOfTransferService logUnregListOfTransferService) {
		this.logUnregListOfTransferService = logUnregListOfTransferService;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	

}
