package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.service.TempUnregPayrollService;

/**
 *
 * @author rizkykojek
 */
public class UnregCalculationSalaryReader implements ItemReader<TempUnregPayroll> {
	private List<TempUnregPayroll> listObject;
	
	public UnregCalculationSalaryReader(TempUnregPayrollService tempUnregPayrollService, Long unregSalaryId, Date createdOn, String createdBy) throws Exception{
		//get populated data
		listObject = tempUnregPayrollService.getAllDataCalculatedPayment(unregSalaryId, createdOn, createdBy);
	}
	
	@Override
	public TempUnregPayroll read() throws Exception, UnexpectedInputException,ParseException, NonTransientResourceException {
		TempUnregPayroll object = null;
		
		if(listObject.size() > 0){
			object = listObject.remove(0);
		}
		
		return object;
	}

}
