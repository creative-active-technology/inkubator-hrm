package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CareerTransitionDao;
import com.inkubator.hrm.entity.CareerTransition;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.CareerTransitionSearchParameter;

@Repository(value = "careerTransitionDao")
@Lazy
public class CareerTransitionDaoImpl extends IDAOImpl<CareerTransition> implements CareerTransitionDao{

	@Override
	public Class<CareerTransition> getEntityClass() {
		return CareerTransition.class;
	}
	
	@Override
	public List<CareerTransition> getByParam(CareerTransitionSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(CareerTransitionSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public CareerTransition getEntityByPKWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("careerEmpStatus", FetchMode.JOIN);
        criteria.setFetchMode("careerTerminationType", FetchMode.JOIN);
        criteria.setFetchMode("systemCareerConst", FetchMode.JOIN);
        criteria.setFetchMode("systemLetterReference", FetchMode.JOIN);
        return (CareerTransition) criteria.uniqueResult();
	}
	
	public void doSearchByParam(CareerTransitionSearchParameter searchParameter, Criteria criteria){
		if (searchParameter.getTransitionCode()!= null) {
            criteria.add(Restrictions.like("transitionCode", searchParameter.getTransitionCode(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getTransitionName() != null) {
            criteria.add(Restrictions.like("transitionName", searchParameter.getTransitionName(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getTransitionRole() != null) {
            criteria.add(Restrictions.like("transitionRole", searchParameter.getTransitionRole(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
	}

}
