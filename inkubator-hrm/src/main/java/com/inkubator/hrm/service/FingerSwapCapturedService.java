
package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.FingerSwapCaptured;
import com.inkubator.hrm.web.model.FingerSwapCapturedBatchModel;
import com.inkubator.hrm.web.model.FingerSwapCapturedViewModel;
import com.inkubator.hrm.web.search.FingerSwapCapturedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface FingerSwapCapturedService extends IService<FingerSwapCaptured> {

	public List<FingerSwapCapturedViewModel> getAllDataByParam(FingerSwapCapturedSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(FingerSwapCapturedSearchParameter parameter) throws Exception;
	
	public void executeBatchProcess(FingerSwapCapturedBatchModel model) throws Exception;
	
	public Boolean isDataSwapOnPeriodDateStillEmpty(Date startDate, Date endDate) throws Exception;
}
