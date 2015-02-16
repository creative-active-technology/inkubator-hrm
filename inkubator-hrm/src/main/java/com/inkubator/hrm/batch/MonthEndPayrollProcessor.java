package com.inkubator.hrm.batch;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.entity.PayTempKalkulasi;

/**
 *
 * @author rizkykojek
 */
public class MonthEndPayrollProcessor implements ItemProcessor<PayTempKalkulasi, LogMonthEndPayroll> {

	private Long periodeId;
	private Date periodeStart;
	private Date periodeEnd;
	private String createdBy;
	private Date createdOn;
	
	@Override
	public LogMonthEndPayroll process(PayTempKalkulasi item) throws Exception {
		LogMonthEndPayroll out = new LogMonthEndPayroll();
		out.setEmpDataId(item.getEmpData().getId());		
		out.setEmpName(item.getEmpData().getBioData().getFullName());
		out.setEmpNik(item.getEmpData().getNik());
		out.setEmpJabatanCode(item.getEmpData().getJabatanByJabatanId().getCode());
		out.setEmpJabatanName(item.getEmpData().getJabatanByJabatanId().getName());
		out.setEmpGolJabatan(item.getEmpData().getGolonganJabatan().getCode());
		out.setEmpStatus(item.getEmpData().getEmployeeType().getName());
		out.setFactor(item.getFactor());
		out.setModelCompSpecific(item.getPaySalaryComponent().getModelComponent().getSpesific());
		out.setNominal(item.getNominal());
		out.setPaySalaryCompId(item.getPaySalaryComponent().getId());
		out.setPaySalaryCompCode(item.getPaySalaryComponent().getCode());
		out.setPeriodeId(periodeId);
		out.setPeriodeStart(periodeStart);
		out.setPeriodeEnd(periodeEnd);
		out.setCreatedBy(createdBy);
		out.setCreatedOn(createdOn);
		
		String paySalaryDesc = item.getPaySalaryComponent().getName();
		if(StringUtils.isNotEmpty(item.getDetail())){
			paySalaryDesc = paySalaryDesc + " (" + item.getDetail() + ")";
		}
		out.setPaySalaryDesc(paySalaryDesc);		
		
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
