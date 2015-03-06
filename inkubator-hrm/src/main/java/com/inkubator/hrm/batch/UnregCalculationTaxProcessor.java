package com.inkubator.hrm.batch;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.springframework.batch.item.ItemProcessor;

import ch.lambdaj.Lambda;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.entity.TaxRate;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.service.TaxRateService;

/**
 *
 * @author rizkykojek
 */
public class UnregCalculationTaxProcessor implements ItemProcessor<List<TempUnregPayroll>, List<TempUnregPayrollEmpPajak>> {

	private List<TaxRate> taxRates;
	private Date createdOn; 
	private String createdBy;
	private transient Logger LOGGER = Logger.getLogger(getClass());
	
	public UnregCalculationTaxProcessor(TaxRateService taxRateService){
		try {
			taxRates = taxRateService.getAllData();
			taxRates = Lambda.sort(taxRates, Lambda.on(TaxRate.class).getLowRate());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	@Override
	public List<TempUnregPayrollEmpPajak> process(List<TempUnregPayroll> items) throws Exception {
		List<TempUnregPayrollEmpPajak> outputs = new ArrayList<TempUnregPayrollEmpPajak>();
		for(long id=1; id<=23; id++){			
			Double nominal = 0.0; 
			//filtering by tax component ID
			List<TempUnregPayroll> temps = Lambda.select(items, Lambda.having(Lambda.on(TempUnregPayroll.class).getPaySalaryComponent().getTaxComponent().getId(), Matchers.equalTo(id)));
			if(!temps.isEmpty()){
				nominal = Lambda.sum(temps, Lambda.on(TempUnregPayroll.class).getNominal()).doubleValue();
			}
			
			TempUnregPayrollEmpPajak pajak = new TempUnregPayrollEmpPajak();
			pajak.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
			pajak.setTaxComponent(new TaxComponent(id));
			pajak.setEmpData(items.get(0).getEmpData());
			pajak.setUnregSalary(items.get(0).getUnregSalary());
			pajak.setNominal(nominal);			
			
			pajak.setCreatedBy(createdBy);
			pajak.setCreatedOn(createdOn);
            outputs.add(pajak);
		}
		
		double nominal_pajak_7 = Lambda.sum(Lambda.select(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.isIn(new Long[]{1L,3L,4L,5L,6L}))), Lambda.on(TempUnregPayrollEmpPajak.class).getNominal());
		TempUnregPayrollEmpPajak pajak_7 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(7L)));
		pajak_7.setNominal(nominal_pajak_7);
		
		TempUnregPayrollEmpPajak pajak_8 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(8L)));
		TempUnregPayrollEmpPajak pajak_9 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(9L)));
		pajak_9.setNominal(pajak_7.getNominal() + pajak_8.getNominal());
		
		TempUnregPayrollEmpPajak pajak_10 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(10L)));
		pajak_10.setNominal(0.05 * pajak_7.getNominal());
		
		TempUnregPayrollEmpPajak pajak_11 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(11L)));
		TempUnregPayrollEmpPajak pajak_12 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(12L)));
		TempUnregPayrollEmpPajak pajak_13 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(13L)));
		pajak_13.setNominal(pajak_10.getNominal() + pajak_11.getNominal() +  pajak_12.getNominal());
		
		TempUnregPayrollEmpPajak pajak_14 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(14L)));
		pajak_14.setNominal(pajak_9.getNominal() - pajak_13.getNominal());
		
		TempUnregPayrollEmpPajak pajak_16 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(16L)));
		pajak_16.setNominal(12 * pajak_14.getNominal());
		
		TempUnregPayrollEmpPajak pajak_17 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(17L)));
		pajak_17.setNominal(pajak_17.getEmpData().getTaxFree().getFreeNominal().doubleValue());
		
		TempUnregPayrollEmpPajak pajak_18 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(18L)));
		pajak_18.setNominal(pajak_16.getNominal() - pajak_17.getNominal());
		
		TempUnregPayrollEmpPajak pajak_19 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(19L)));		
		pajak_19.setNominal(this.getTaxProgressive(pajak_18.getNominal()));
		
		TempUnregPayrollEmpPajak pajak_21 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(21L)));
		pajak_21.setNominal(this.roundingHundred(pajak_19.getNominal() / 12));
		
		
		/** START gross up calculation (optional per company)
		 * (jika employee bayar pajak sendiri, comment code dibawah ini sampai END tag) */ 
		TempUnregPayrollEmpPajak pajak_2 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(2L)));
		pajak_2.setNominal(pajak_21.getNominal());
		pajak_7.setNominal(pajak_7.getNominal() + pajak_2.getNominal());
		pajak_9.setNominal(pajak_9.getNominal() + pajak_2.getNominal());
		pajak_10.setNominal(0.05 * pajak_7.getNominal());
		pajak_13.setNominal(pajak_10.getNominal() + pajak_11.getNominal() +  pajak_12.getNominal());
		pajak_14.setNominal(pajak_9.getNominal() - pajak_13.getNominal());
		pajak_16.setNominal(12 * pajak_14.getNominal());
		pajak_18.setNominal(pajak_16.getNominal() - pajak_17.getNominal());
		pajak_19.setNominal(this.getTaxProgressive(pajak_18.getNominal()));		
		pajak_21.setNominal(this.roundingHundred(pajak_19.getNominal() / 12));
		/** END gross up calculation */
		
		//Final TAX
		TempUnregPayrollEmpPajak pajak_23 = Lambda.selectFirst(outputs, Lambda.having(Lambda.on(TempUnregPayrollEmpPajak.class).getTaxComponent().getId(), Matchers.equalTo(23L)));
		pajak_23.setNominal(pajak_21.getNominal());
		
		return outputs;
	}
	
	private Double getTaxProgressive(double nominal){
		double tax = 0.0;
		Iterator<TaxRate> iter = taxRates.iterator();
		for(;iter.hasNext();){
			TaxRate taxRate = iter.next();
			
			if(nominal > taxRate.getTopRate()) {
				if(iter.hasNext()){
					tax = tax + (((taxRate.getTopRate() * taxRate.getPercentRate()) / 100));				
					nominal = nominal - taxRate.getTopRate();
				} else {
					tax = tax + (((nominal * taxRate.getPercentRate()) / 100));
				}
			} else {
				tax = tax + (((nominal * taxRate.getPercentRate()) / 100));
				break;
			}
		}
		
		return tax;
	}
	
	private Double roundingHundred(double nominal){
		
		nominal = nominal / 100;
		nominal = Math.ceil(nominal);
		nominal = nominal * 100;
		
		return nominal;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
}
