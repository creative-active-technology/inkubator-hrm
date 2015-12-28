package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyUnitIndicatorDao extends IDAO<AppraisalCompetencyUnitIndicator> {

	public List<AppraisalCompetencyUnitIndicator> getAllDataByCompetencyUnitId(Long competencyUnitId);

	public AppraisalCompetencyUnitIndicator getEntityByIdWithDetail(Long id);

	public Long getTotalByIndicatorAndCompetencyUnitId(String indicator, Long competencyUnitId);

	public Long getTotalByIndicatorAndCompetencyUnitIdAndNotId(String indicator, Long competencyUnitId, Long id);
	
	public Long getTotalByLevelIndexAndCompetencyUnitId(Integer levelIndex, Long competencyUnitId);

	public Long getTotalByLevelIndexAndCompetencyUnitIdAndNotId(Integer levelIndex, Long competencyUnitId, Long id);

}
