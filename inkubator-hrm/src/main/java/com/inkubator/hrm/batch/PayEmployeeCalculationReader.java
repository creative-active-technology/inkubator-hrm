package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.PayTempKalkulasiService;

/**
 *
 * @author rizkykojek
 */
public class PayEmployeeCalculationReader implements ItemReader<PayTempKalkulasi> {
	private List<PayTempKalkulasi> listObject;
	
	public PayEmployeeCalculationReader(PayTempKalkulasiService payTempKalkulasiService, Date startPeriodDate, Date endPeriodDate, Date createdOn, String createdBy) throws Exception{
		//get populated data
		listObject = payTempKalkulasiService.getAllDataCalculatedPayment(startPeriodDate, endPeriodDate, createdOn, createdBy);
	}
	
	@Override
	public PayTempKalkulasi read() throws Exception, UnexpectedInputException,ParseException, NonTransientResourceException {
		PayTempKalkulasi object = null;
		
		if(listObject.size() > 0){
			object = listObject.remove(0);
		}
		
		return object;
	}

}
