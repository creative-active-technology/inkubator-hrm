package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalPerformanceIndicatorDao extends IDAO<AppraisalPerformanceIndicator> {
	public List<AppraisalPerformanceIndicator> getListByIdAppraisalPerformanceGroup(Long idAppraisalPerformanceGroup);
	public AppraisalPerformanceIndicator getEntityWithDetailById(Long id);
}
