package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.LogMonthEndTaxes;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;

/**
 *
 * @author rizkykojek
 */
public class MonthEndTaxesProcessor implements ItemProcessor<PayTempKalkulasiEmpPajak, LogMonthEndTaxes> {

	private Long periodeId;
	private Date periodeStart;
	private Date periodeEnd;
	private String createdBy;
	private Date createdOn;
	
	@Override
	public LogMonthEndTaxes process(PayTempKalkulasiEmpPajak item) throws Exception {
		LogMonthEndTaxes out = new LogMonthEndTaxes();
		out.setEmpDataId(item.getEmpData().getId());		
		out.setEmpName(item.getEmpData().getBioData().getFullName());
		out.setEmpNik(item.getEmpData().getNik());
		out.setEmpJabatanId(item.getEmpData().getJabatanByJabatanId().getId());
		out.setEmpJabatanCode(item.getEmpData().getJabatanByJabatanId().getCode());
		out.setEmpJabatanName(item.getEmpData().getJabatanByJabatanId().getName());
		out.setEmpGolJabatan(item.getEmpData().getGolonganJabatan().getCode());
		out.setEmpStatus(item.getEmpData().getEmployeeType().getName());
		out.setDepartmentId(item.getEmpData().getJabatanByJabatanId().getDepartment().getId());
		out.setDepartmentName(item.getEmpData().getJabatanByJabatanId().getDepartment().getDepartmentName());
		out.setNominal(item.getNominal());
		out.setTaxCompId(item.getTaxComponent().getId());
		out.setTaxCompName(item.getTaxComponent().getName());
		out.setPeriodeId(periodeId);
		out.setPeriodeStart(periodeStart);
		out.setPeriodeEnd(periodeEnd);
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
