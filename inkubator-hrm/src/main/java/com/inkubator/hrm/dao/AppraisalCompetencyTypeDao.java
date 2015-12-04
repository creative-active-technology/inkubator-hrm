package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.web.search.AppraisalCompetencyTypeSearchParameter;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface AppraisalCompetencyTypeDao extends IDAO<AppraisalCompetencyType> {
	public List<AppraisalCompetencyType> getListByParam(AppraisalCompetencyTypeSearchParameter searchParameter,	int firstResult, int maxResult, Order order);
	
	public Long getTotalByParam(AppraisalCompetencyTypeSearchParameter searchParameter);
}
