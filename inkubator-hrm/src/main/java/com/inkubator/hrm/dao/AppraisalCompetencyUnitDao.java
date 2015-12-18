package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.web.search.CompetencyUnitSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyUnitDao extends IDAO<AppraisalCompetencyUnit> {
	
	public List<AppraisalCompetencyUnit> getAllDataByParam(CompetencyUnitSearchParameter searchParameter, int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(CompetencyUnitSearchParameter searchParameter);

	public AppraisalCompetencyUnit getEntityByIdWithDetail(Long id);

}
