package com.inkubator.hrm.batch;

import java.util.HashMap;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author rizkykojek
 */
public class UnregCalculationFinalProcessor implements ItemProcessor<EmpData, HashMap<String, Long>> {

	private Long unregSalaryId;
	
	public UnregCalculationFinalProcessor(Long unregSalaryId){
		this.unregSalaryId = unregSalaryId;
	}
	@Override
	public HashMap<String, Long> process(EmpData item) throws Exception {
		HashMap<String, Long> output = new HashMap<String, Long>();
		output.put("empDataId", item.getId());
		output.put("unregSalaryId", unregSalaryId);
		
		return output;
	}

}
