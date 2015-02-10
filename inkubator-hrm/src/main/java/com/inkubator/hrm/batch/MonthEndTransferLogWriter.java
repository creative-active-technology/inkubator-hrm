package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LogListOfTransferService;
import com.inkubator.hrm.service.WtPeriodeService;

/**
*
* @author rizkykojek
*/
public class MonthEndTransferLogWriter implements ItemWriter<PayTempKalkulasi> {

	private LogListOfTransferService logListOfTransferService;
	private WtPeriodeService wtPeriodeService;
	private WtPeriode periode;
	private Long periodeId;
	private String createdBy;
	private Date createdOn;
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) throws Exception {
		periode = wtPeriodeService.getEntiyByPK(periodeId);
	}
	
	@Override
	public void write(List<? extends PayTempKalkulasi> items) throws Exception {
		for(PayTempKalkulasi payTempKalkulasi : items){
			logListOfTransferService.executeBatchMonthEndPayroll(payTempKalkulasi, periode, createdBy, createdOn);
		}		
	}

	public LogListOfTransferService getLogListOfTransferService() {
		return logListOfTransferService;
	}

	public void setLogListOfTransferService(
			LogListOfTransferService logListOfTransferService) {
		this.logListOfTransferService = logListOfTransferService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public WtPeriode getPeriode() {
		return periode;
	}

	public void setPeriode(WtPeriode periode) {
		this.periode = periode;
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

	public Long getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(Long periodeId) {
		this.periodeId = periodeId;
	}

}
