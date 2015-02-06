package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.service.WtPeriodeService;

/**
*
* @author rizkykojek
*/
public class DataFingerRealizationWriter implements ItemWriter<EmpData> {

	private TempProcessReadFingerService tempProcessReadFingerService;
	private WtPeriodeService wtPeriodeService;
	private WtPeriode periode;
	private String createdBy;
	private Date createdOn;
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) throws Exception {
		periode = wtPeriodeService.getEntityByAbsentTypeActive();
		
		/** delete all record in that period, kecuali yang sudah di correction(baik yg in atau out)*/
		tempProcessReadFingerService.deleteByScheduleDateAndIsNotCorrection(periode.getFromPeriode(), periode.getUntilPeriode());
	}
	
	@Override
	public void write(List<? extends EmpData> items) throws Exception {
		for(EmpData empData : items){
			tempProcessReadFingerService.synchDataFingerRealization(empData, periode, createdBy, createdOn);
		}		
	}
	
	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public TempProcessReadFingerService getTempProcessReadFingerService() {
		return tempProcessReadFingerService;
	}

	public void setTempProcessReadFingerService(TempProcessReadFingerService tempProcessReadFingerService) {
		this.tempProcessReadFingerService = tempProcessReadFingerService;
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
