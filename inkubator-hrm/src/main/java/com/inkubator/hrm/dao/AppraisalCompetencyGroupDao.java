package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyGroupDao extends IDAO<AppraisalCompetencyGroup> {
	
	public List<AppraisalCompetencyGroup> getAllDataByParam(CompetencyGroupSearchParameter searchParameter,	int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(CompetencyGroupSearchParameter searchParameter);

	public AppraisalCompetencyGroup getEntityByIdWithDetail(Long id);
	
	public List<AppraisalCompetencyGroup> getAllDataByCompetencyTypeId(Long competencyTypeId);

}
