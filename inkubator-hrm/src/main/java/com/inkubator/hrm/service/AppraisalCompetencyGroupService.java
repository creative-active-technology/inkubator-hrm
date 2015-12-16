package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyGroupService extends IService<AppraisalCompetencyGroup> {

	public List<AppraisalCompetencyGroup> getAllDataByParam(CompetencyGroupSearchParameter searchParameter,	int firstResult, int maxResult, Order order) throws Exception;
	
	public Long getTotalByParam(CompetencyGroupSearchParameter searchParameter)  throws Exception;

	public AppraisalCompetencyGroup getEntityByIdWithDetail(Long id);
	
}
