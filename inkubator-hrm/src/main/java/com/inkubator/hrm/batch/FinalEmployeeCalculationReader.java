package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;

/**
 *
 * @author rizkykojek
 */
public class FinalEmployeeCalculationReader implements ItemReader<EmpData> {
	private List<EmpData> listEmployee;
	
	public FinalEmployeeCalculationReader(EmpDataService empDataService, Date endPeriodDate) throws Exception{
		//get populated data
		listEmployee = empDataService.getAllDataNotTerminateAndJoinDateLowerThan(endPeriodDate);
	}

	@Override
	public EmpData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		EmpData object = null;
		
		if(listEmployee.size() > 0){
			object	 = listEmployee.remove(0);
		}
		
		return object;
		
	}

	
	

}
