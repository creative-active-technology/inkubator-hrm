package com.inkubator.hrm.service;

import java.util.List;
import java.util.Map;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicatorJabatan;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalPerformanceIndicatorJabatanService extends IService<AppraisalPerformanceIndicatorJabatan> {

	public List<AppraisalPerformanceIndicatorJabatan> getAllDataByJabatanIdFetchScoringIndex(Long jabatanId) throws Exception;

	public void saveOrUpdate(Long jabatanId, Map<Long, Long> mapIndicatorScoreIndex);
	
}
