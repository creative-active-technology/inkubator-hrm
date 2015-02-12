package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.SalaryJournalModel;

/**
*
* @author rizkykojek
*/
public class MonthEndJournalLogProcessor implements ItemProcessor<SalaryJournalModel, LogSalaryJournal> {

	private WtPeriode periode;
	private String createdBy;
	private Date createdOn;
	
	public MonthEndJournalLogProcessor(WtPeriodeService wtPeriodeService, Long periodeId) throws Exception{
		periode = wtPeriodeService.getEntiyByPK(periodeId);
	}
	
	@Override
	public LogSalaryJournal process(SalaryJournalModel item) throws Exception {
		LogSalaryJournal log = new LogSalaryJournal();
		log.setWtPeriode(periode);
		log.setCostCenterId(item.getCostCenterId());
		log.setCostCenterCode(item.getCostCenterCode());
		log.setCostCenterName(item.getCostCenterName());
		log.setJournalId(item.getJurnalId());
		log.setJournalCode(item.getJurnalCode());
		log.setJournalName(item.getJurnalName());
		log.setDebet(item.getDebet());
		log.setCredit(item.getKredit());
		log.setCreatedBy(createdBy);
		log.setCreatedOn(createdOn);
		return log;
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
