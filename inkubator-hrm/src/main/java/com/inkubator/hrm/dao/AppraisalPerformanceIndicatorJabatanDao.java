package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicatorJabatan;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalPerformanceIndicatorJabatanDao extends IDAO<AppraisalPerformanceIndicatorJabatan> {

	public List<AppraisalPerformanceIndicatorJabatan> getAllDataByJabatanIdFetchScoringIndex(Long jabatanId);

	public void deleteByJabatanId(Long jabatanId);
	
}
