package com.inkubator.hrm.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.UnregDepartement;
import com.inkubator.hrm.entity.UnregEmpReligion;
import com.inkubator.hrm.entity.UnregEmpType;
import com.inkubator.hrm.entity.UnregGoljab;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.UnregDepartementService;
import com.inkubator.hrm.service.UnregEmpReligionService;
import com.inkubator.hrm.service.UnregEmpTypeService;
import com.inkubator.hrm.service.UnregGoljabService;

/**
 *
 * @author rizkykojek
 */
public class UnregCalculationFinalReader implements ItemReader<EmpData> {
	private List<EmpData> listEmployee;
	
	public UnregCalculationFinalReader(Long unregSalaryId, UnregDepartementService unregDepartementService, UnregEmpReligionService unregEmpReligionService, 
			UnregGoljabService unregGoljabService, UnregEmpTypeService unregEmpTypeService, EmpDataService empDataService) throws Exception{
		//get populated data
		List<UnregDepartement> listDepartements = unregDepartementService.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregEmpReligion> listReligions = unregEmpReligionService.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregGoljab> listGoljabs = unregGoljabService.getAllDataByUnregSalaryId(unregSalaryId);
		List<UnregEmpType> listEmpTypes = unregEmpTypeService.getAllDataByUnregSalaryId(unregSalaryId);		
		List<Long> departmentIds = Lambda.extract(listDepartements, Lambda.on(UnregDepartement.class).getId().getUnregDepartementId());
		List<Long> religionIds = Lambda.extract(listReligions, Lambda.on(UnregEmpReligion.class).getId().getReligionId());
		List<Long> golJabIds = Lambda.extract(listGoljabs, Lambda.on(UnregGoljab.class).getId().getGolonganJabatanId());
		List<Long> empTypeIds = Lambda.extract(listEmpTypes, Lambda.on(UnregEmpType.class).getId().getUnregEmpTypeId());
		listEmployee = empDataService.getAllDataByDepartmentAndReligionAndGolJabAndEmpType(departmentIds, religionIds, golJabIds, empTypeIds);
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
