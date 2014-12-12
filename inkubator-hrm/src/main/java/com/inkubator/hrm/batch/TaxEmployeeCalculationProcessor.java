package com.inkubator.hrm.batch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.springframework.batch.item.ItemProcessor;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.entity.TaxComponent;

/**
 *
 * @author rizkykojek
 */
public class TaxEmployeeCalculationProcessor implements ItemProcessor<List<PayTempKalkulasi>, List<PayTempKalkulasiEmpPajak>> {

	private Date payrollCalculationDate; 
	private String createdBy;
	
	@Override
	public List<PayTempKalkulasiEmpPajak> process(List<PayTempKalkulasi> items) throws Exception {
		List<PayTempKalkulasiEmpPajak> outputs = new ArrayList<PayTempKalkulasiEmpPajak>();
		for(long id=1; id<=23; id++){			
			Double nominal = 0.0; 
			//filtering by tax component ID
			List<PayTempKalkulasi> temps = Lambda.select(items, Lambda.having(Lambda.on(PayTempKalkulasi.class).getPaySalaryComponent().getTaxComponent().getId(), Matchers.equalTo(id)));
			if(!temps.isEmpty()){
				nominal = Lambda.sum(temps, Lambda.on(PayTempKalkulasi.class).getNominal()).doubleValue();
			}
			
			PayTempKalkulasiEmpPajak pajak = new PayTempKalkulasiEmpPajak();
			pajak.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
			pajak.setTaxComponent(new TaxComponent(id));
			pajak.setEmpData(items.get(0).getEmpData());
			pajak.setNominal(nominal);
			
			pajak.setCreatedBy(createdBy);
			pajak.setCreatedOn(payrollCalculationDate);
            outputs.add(pajak);
		}
		
		double nominal_pajak_7 = Lambda.sum(Lambda.select(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.isIn(new Long[]{1L,3L,4L,5L,6L}))), Lambda.on(PayTempKalkulasiEmpPajak.class).getNominal()).doubleValue();
		PayTempKalkulasiEmpPajak pajak_7 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(7L)));
		pajak_7.setNominal(nominal_pajak_7);
		
		PayTempKalkulasiEmpPajak pajak_8 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(8L)));
		PayTempKalkulasiEmpPajak pajak_9 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(9L)));
		pajak_9.setNominal(pajak_7.getNominal() + pajak_8.getNominal());
		
		PayTempKalkulasiEmpPajak pajak_10 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(10L)));
		pajak_10.setNominal(0.05 * pajak_7.getNominal());
		
		PayTempKalkulasiEmpPajak pajak_11 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(11L)));
		PayTempKalkulasiEmpPajak pajak_12 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(12L)));
		PayTempKalkulasiEmpPajak pajak_13 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(13L)));
		pajak_13.setNominal(pajak_10.getNominal() + pajak_11.getNominal() +  pajak_12.getNominal());
		
		PayTempKalkulasiEmpPajak pajak_14 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(14L)));
		pajak_14.setNominal(pajak_9.getNominal() - pajak_13.getNominal());
		
		PayTempKalkulasiEmpPajak pajak_16 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(PayTempKalkulasiEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(16L)));
		pajak_16.setNominal(12 * pajak_14.getNominal());
		
		return outputs;
	}

	public Date getPayrollCalculationDate() {
		return payrollCalculationDate;
	}

	public void setPayrollCalculationDate(Date payrollCalculationDate) {
		this.payrollCalculationDate = payrollCalculationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	

}
