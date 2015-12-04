package com.inkubator.hrm.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyTypeDao;
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.web.search.AppraisalCompetencyTypeSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/

@Repository(value = "appraisalCompetencyTypeDao")
@Lazy
public class AppraisalCompetencyTypeDaoImpl extends IDAOImpl<AppraisalCompetencyType> implements AppraisalCompetencyTypeDao {

	@Override
	public Class<AppraisalCompetencyType> getEntityClass() {
		return AppraisalCompetencyType.class;
	}

	@Override
	public List<AppraisalCompetencyType> getListByParam(AppraisalCompetencyTypeSearchParameter searchParameter,	int firstResult, int maxResult, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAppraisalCompetencyByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(AppraisalCompetencyTypeSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAppraisalCompetencyByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchAppraisalCompetencyByParam(AppraisalCompetencyTypeSearchParameter parameter, Criteria criteria) {
        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (parameter.getCode() != null){
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

}
