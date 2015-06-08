package com.inkubator.hrm.batch;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.web.model.FingerSwapCapturedBatchModel;

/**
 *
 * @author rizkykojek
 */
public class FingerSwapCapturedDownloadXmlProcessor implements ItemProcessor<FingerSwapCapturedBatchModel, FingerSwapCapturedBatchModel> {

	private Long machineId;
	
	@Override
	public FingerSwapCapturedBatchModel process(FingerSwapCapturedBatchModel item) throws Exception {
		item.setMachineId(machineId);		
		return item;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	

}
