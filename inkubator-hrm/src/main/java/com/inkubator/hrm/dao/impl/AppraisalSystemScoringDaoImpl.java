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
import com.inkubator.hrm.dao.AppraisalSystemScoringDao;
import com.inkubator.hrm.entity.AppraisalSystemScoring;
import com.inkubator.hrm.web.search.AppraisalSystemScoringSearchParameter;
import com.inkubator.hrm.web.search.SavingTypeSearchParameter;

@Repository(value = "appraisalSystemScoringDao")
@Lazy
public class AppraisalSystemScoringDaoImpl extends IDAOImpl<AppraisalSystemScoring> implements AppraisalSystemScoringDao {

	@Override
	public Class<AppraisalSystemScoring> getEntityClass() {
		return AppraisalSystemScoring.class;
	}

	@Override
	public List<AppraisalSystemScoring> getByParam(AppraisalSystemScoringSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(AppraisalSystemScoringSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchByParam(AppraisalSystemScoringSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
}
