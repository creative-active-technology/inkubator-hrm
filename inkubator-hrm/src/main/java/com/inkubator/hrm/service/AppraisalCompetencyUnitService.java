package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.web.search.CompetencyUnitSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalCompetencyUnitService extends IService<AppraisalCompetencyUnit> {

	public List<AppraisalCompetencyUnit> getAllDataByParam(CompetencyUnitSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception;
	
	public Long getTotalByParam(CompetencyUnitSearchParameter searchParameter) throws Exception;

	public AppraisalCompetencyUnit getEntityByIdWithDetail(Long id) throws Exception;
	
}
