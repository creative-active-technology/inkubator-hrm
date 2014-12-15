package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;

/**
 *
 * @author rizkykojek
 */
public class TaxEmployeeCalculationWriter extends JdbcBatchItemWriter<PayTempKalkulasiEmpPajak> {

	@Override
	public void write(List<? extends PayTempKalkulasiEmpPajak> items)throws Exception {
		for(int i=0;i < items.size(); i++){
			List<PayTempKalkulasiEmpPajak> pajaks =  (List<PayTempKalkulasiEmpPajak>) items.get(i);
			super.write(pajaks);
		}
		
	}
}
