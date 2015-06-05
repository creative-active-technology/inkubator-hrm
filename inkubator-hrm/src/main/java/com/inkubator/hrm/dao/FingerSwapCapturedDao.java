package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FingerSwapCaptured;
import com.inkubator.hrm.web.model.FingerSwapCapturedViewModel;
import com.inkubator.hrm.web.search.FingerSwapCapturedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface FingerSwapCapturedDao extends IDAO<FingerSwapCaptured> {

	public List<FingerSwapCaptured> getAllDataByFingerIndexIdAndSwapDatetimeLogBetween(List<String> fingerIndexIds, Date startDate, Date endDate, Order order);

	public List<FingerSwapCapturedViewModel> getAllDataByParam(FingerSwapCapturedSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(FingerSwapCapturedSearchParameter parameter);
}
