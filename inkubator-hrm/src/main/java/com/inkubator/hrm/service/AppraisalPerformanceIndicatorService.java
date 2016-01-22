package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalPerformanceIndicatorService extends IService<AppraisalPerformanceIndicator> {
	public List<AppraisalPerformanceIndicator> getListByIdAppraisalPerformanceGroup(Long idAppraisalPerformanceGroup) throws Exception;
	public AppraisalPerformanceIndicator getEntityWithDetailById(Long id);
}
