package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class TempAttendanceRealizationCalculationReader implements ItemReader<EmpData> {
	private List<EmpData> listEmpData;
	
	public TempAttendanceRealizationCalculationReader(EmpDataService empDataService, Date periodUntillDate) throws Exception{
		//Populated List Employee Data
		listEmpData = empDataService.getAllDataAllCompanyNotTerminateAndJoinDateLowerThan(periodUntillDate);		
	}
	
	@Override
	public EmpData read() throws Exception, UnexpectedInputException,ParseException, NonTransientResourceException {
		EmpData empData = null;
		
		if(listEmpData.size() > 0){
			empData = listEmpData.remove(0);
		}
		
		return empData;
	}
	
	
	public void destroy()  {
		//should close reader
		
	}

}
