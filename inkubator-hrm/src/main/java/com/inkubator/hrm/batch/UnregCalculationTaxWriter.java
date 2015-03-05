package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;

/**
 *
 * @author rizkykojek
 */
public class UnregCalculationTaxWriter extends JdbcBatchItemWriter<TempUnregPayrollEmpPajak> {

	@Override
	public void write(List<? extends TempUnregPayrollEmpPajak> items)throws Exception {
		for(int i=0;i < items.size(); i++){
			List<TempUnregPayrollEmpPajak> pajaks =  (List<TempUnregPayrollEmpPajak>) items.get(i);
			super.write(pajaks);
		}		
	}
}
