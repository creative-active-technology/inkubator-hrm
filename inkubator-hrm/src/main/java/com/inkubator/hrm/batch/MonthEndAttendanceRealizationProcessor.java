package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.entity.TempAttendanceRealization;

/**
 *
 * @author rizkykojek
 */
public class MonthEndAttendanceRealizationProcessor implements ItemProcessor<TempAttendanceRealization, LogWtAttendanceRealization> {

	private Long periodeId;
	private Date periodeStart;
	private Date periodeEnd;
	private String createdBy;
	private Date createdOn;
	
	@Override
	public LogWtAttendanceRealization process(TempAttendanceRealization item) throws Exception {
		LogWtAttendanceRealization out = new LogWtAttendanceRealization();
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
		out.setWtGroupWorkingId(item.getWtGroupWorking().getId());
		out.setWtGroupWorkingName(item.getWtGroupWorking().getName());	
		out.setAttendanceDaysPresent(item.getAttendanceDaysPresent());
		out.setAttendanceDaysSchedule(item.getAttendanceDaysSchedule());
		out.setDuty(item.getDuty());
		out.setLeaves(item.getLeave());
		out.setPermit(item.getPermit());
		out.setOvertime(item.getOvertime());
		out.setSick(item.getSick());
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
