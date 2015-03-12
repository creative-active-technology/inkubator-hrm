package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.LogUnregTaxes;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;

/**
 *
 * @author rizkykojek
 */
public class UnregPayrollTaxesProcessor implements ItemProcessor<TempUnregPayrollEmpPajak, LogUnregTaxes> {

	private String createdBy;
	private Date createdOn;
	
	@Override
	public LogUnregTaxes process(TempUnregPayrollEmpPajak item) throws Exception {
		LogUnregTaxes out = new LogUnregTaxes();
		out.setUnregSalaryId(item.getUnregSalary().getId());
		out.setUnregSalaryName(item.getUnregSalary().getName());
		out.setUnregSalaryPaymentDate(item.getUnregSalary().getSalaryDate());
		out.setUnregSalaryStartPeriod(item.getUnregSalary().getStartPeriodDate());
		out.setUnregSalaryEndPeriod(item.getUnregSalary().getEndPeriodDate());
		out.setEmpDataId(item.getEmpData().getId());		
		out.setEmpName(item.getEmpData().getBioData().getFullName());
		out.setEmpNik(item.getEmpData().getNik());
		out.setEmpJabatanId(item.getEmpData().getJabatanByJabatanId().getId());
		out.setEmpJabatanCode(item.getEmpData().getJabatanByJabatanId().getCode());
		out.setEmpJabatanName(item.getEmpData().getJabatanByJabatanId().getName());
		out.setEmpGolJabatan(item.getEmpData().getGolonganJabatan().getCode());
		out.setEmpTypeId(item.getEmpData().getEmployeeType().getId());
		out.setEmpTypeName(item.getEmpData().getEmployeeType().getName());
		out.setDepartmentId(item.getEmpData().getJabatanByJabatanId().getDepartment().getId());
		out.setDepartmentName(item.getEmpData().getJabatanByJabatanId().getDepartment().getDepartmentName());
		out.setNominal(item.getNominal());
		out.setTaxCompId(item.getTaxComponent().getId());
		out.setTaxCompName(item.getTaxComponent().getName());
		out.setPeriodeId(item.getUnregSalary().getWtPeriode().getId());
		out.setPeriodeStart(item.getUnregSalary().getWtPeriode().getFromPeriode());
		out.setPeriodeEnd(item.getUnregSalary().getWtPeriode().getUntilPeriode());
		out.setCreatedBy(createdBy);
		out.setCreatedOn(createdOn);		
		
		return out;
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
