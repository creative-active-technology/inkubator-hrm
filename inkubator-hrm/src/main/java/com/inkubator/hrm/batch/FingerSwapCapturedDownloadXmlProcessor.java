package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.web.model.FingerSwapCapturedBatchModel;

/**
 *
 * @author rizkykojek
 */
public class FingerSwapCapturedDownloadXmlProcessor implements ItemProcessor<FingerSwapCapturedBatchModel, FingerSwapCapturedBatchModel> {

	private Long machineId;
	private String createdBy;
	private Date createdOn;
	
	@Override
	public FingerSwapCapturedBatchModel process(FingerSwapCapturedBatchModel item) throws Exception {
		item.setMachineId(machineId);	
		item.setCreatedBy(createdBy);
		item.setCreatedOn(createdOn);
		return item;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}	

}
