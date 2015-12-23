package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyUnitIndicatorService extends IService<AppraisalCompetencyUnitIndicator> {

	public List<AppraisalCompetencyUnitIndicator> getAllDataByCompetencyUnitId(Long competencyUnitId) throws Exception ;

	public AppraisalCompetencyUnitIndicator getEntityByIdWithDetail(Long id);
	
}
