package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.LogWtProcessReadFinger;
import com.inkubator.hrm.entity.TempProcessReadFinger;

/**
 *
 * @author rizkykojek
 */
public class MonthEndProcessReadFingerProcessor implements ItemProcessor<TempProcessReadFinger, LogWtProcessReadFinger> {

	private Long periodeId;
	private Date periodeStart;
	private Date periodeEnd;
	private String createdBy;
	private Date createdOn;
	
	@Override
	public LogWtProcessReadFinger process(TempProcessReadFinger item) throws Exception {
		LogWtProcessReadFinger out = new LogWtProcessReadFinger();
		out.setEmpDataId(item.getEmpData().getId());		
		out.setEmpName(item.getEmpData().getBioData().getFullName());
		out.setEmpNik(item.getEmpData().getNik());
		out.setEmpJabatanId(item.getEmpData().getJabatanByJabatanId().getId());
		out.setEmpJabatanCode(item.getEmpData().getJabatanByJabatanId().getCode());
		out.setEmpJabatanName(item.getEmpData().getJabatanByJabatanId().getName());
		out.setEmpGolJab(item.getEmpData().getGolonganJabatan().getCode());
		out.setEmpTypeId(item.getEmpData().getEmployeeType().getId());
		out.setEmpTypeName(item.getEmpData().getEmployeeType().getName());
		out.setEmpDepartementId(item.getEmpData().getJabatanByJabatanId().getDepartment().getId());
		out.setEmpDepartementName(item.getEmpData().getJabatanByJabatanId().getDepartment().getDepartmentName());
		out.setWorkingHourName(item.getWorkingHourName());
		out.setScheduleDate(item.getScheduleDate());
		out.setScheduleIn(item.getScheduleIn());
		out.setScheduleOut(item.getScheduleOut());
		out.setFingerIn(item.getFingerIn());
		out.setFingerOut(item.getFingerOut());
		out.setWebCheckIn(item.getWebCheckIn());
		out.setWebCheckOut(item.getWebCheckOut());
		out.setIsCorrectionIn(item.getIsCorrectionIn());
		out.setIsCorrectionOut(item.getIsCorrectionOut());
		out.setMarginIn(item.getMarginIn());
		out.setMarginOut(item.getMarginOut());
		out.setWtPeriodeId(periodeId);
		out.setPeriodeDateStart(periodeStart);
		out.setPeriodeDateEnd(periodeEnd);
		out.setCreatedBy(createdBy);
		out.setCreatedOn(createdOn);	
		
		return out;
	}

	public Long getPeriodeId() {
		return periodeId;
	}

	public void setPeriodeId(Long periodeId) {
		this.periodeId = periodeId;
	}

	public Date getPeriodeStart() {
		return periodeStart;
	}

	public void setPeriodeStart(Date periodeStart) {
		this.periodeStart = periodeStart;
	}

	public Date getPeriodeEnd() {
		return periodeEnd;
	}

	public void setPeriodeEnd(Date periodeEnd) {
		this.periodeEnd = periodeEnd;
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
