package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.service.TempUnregPayrollService;

/**
 *
 * @author rizkykojek
 */
public class UnregCalculationTaxReader implements ItemReader<List<TempUnregPayroll>> {
	private List<EmpData> listEmployee;
	private TempUnregPayrollService tempUnregPayrollService;
	
	public UnregCalculationTaxReader(Long unregSalaryId, TempUnregPayrollService tempUnregPayrollService) throws Exception{
		//get populated data		
		this.tempUnregPayrollService = tempUnregPayrollService;
		listEmployee = tempUnregPayrollService.getAllDataEmployeeByUnregSalaryId(unregSalaryId);
	}

	@Override
	public List<TempUnregPayroll> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		List<TempUnregPayroll> object = null;
		
		if(listEmployee.size() > 0){
			EmpData empData = listEmployee.remove(0);
			object = tempUnregPayrollService.getAllDataByEmpDataIdAndTaxNotNull(empData.getId());
		}
		
		return object;		
	}	

}
