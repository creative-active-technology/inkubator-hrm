package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogSalaryJournal;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.SalaryJournalModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class TempAttendanceRealizationCalculationProcessor implements ItemProcessor<EmpData, TempAttendanceRealizationViewModel> {

	private TempAttendanceRealizationService tempAttendanceRealizationService;
	private String createdBy;
	private Long wtPeriodId;
	private Date createdOn;
	
	public TempAttendanceRealizationCalculationProcessor(TempAttendanceRealizationService tempAttendanceRealizationService) throws Exception{
		this.tempAttendanceRealizationService = tempAttendanceRealizationService;
	}
	
	@Override
	public TempAttendanceRealizationViewModel process(EmpData empData) throws Exception {
		TempAttendanceRealizationViewModel tempAttendanceRealizationViewModel = tempAttendanceRealizationService.calculateEmpTempAttendanceRealization(empData.getId(), wtPeriodId);
		tempAttendanceRealizationViewModel.setCreatedBy(createdBy);
		tempAttendanceRealizationViewModel.setCreatedOn(createdOn);
		return tempAttendanceRealizationViewModel;
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

	public Long getWtPeriodId() {
		return wtPeriodId;
	}

	public void setWtPeriodId(Long wtPeriodId) {
		this.wtPeriodId = wtPeriodId;
	}
	
	
}
