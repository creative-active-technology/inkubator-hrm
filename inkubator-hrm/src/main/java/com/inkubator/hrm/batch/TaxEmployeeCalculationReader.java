package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.PayTempKalkulasiService;

/**
 *
 * @author rizkykojek
 */
public class TaxEmployeeCalculationReader implements ItemReader<List<PayTempKalkulasi>> {
	private List<EmpData> listEmployee;
	private PayTempKalkulasiService payTempKalkulasiService;
	
	public TaxEmployeeCalculationReader(EmpDataService empDataService) throws Exception{
		//get populated data
		listEmployee = empDataService.getAllDataNotTerminate();
		/*listEmployee = new ArrayList<EmpData>();
        EmpData emp = empDataService.getEntiyByPK((long)173);
        listEmployee.add(emp);*/
	}

	@Override
	public List<PayTempKalkulasi> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		List<PayTempKalkulasi> object = null;
		
		if(listEmployee.size() > 0){
			EmpData empData = listEmployee.remove(0);
			object = payTempKalkulasiService.getAllDataByEmpDataIdAndTaxNotNull(empData.getId());
		}
		
		return object;
		
	}

	public PayTempKalkulasiService getPayTempKalkulasiService() {
		return payTempKalkulasiService;
	}

	public void setPayTempKalkulasiService(PayTempKalkulasiService payTempKalkulasiService) {
		this.payTempKalkulasiService = payTempKalkulasiService;
	}
	
	

}
