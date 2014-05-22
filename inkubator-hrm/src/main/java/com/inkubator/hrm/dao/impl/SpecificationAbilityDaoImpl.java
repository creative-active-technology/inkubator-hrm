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
import com.inkubator.hrm.dao.SpecificationAbilityDao;
import com.inkubator.hrm.entity.SpecificationAbility;
import com.inkubator.hrm.web.search.SpecificationAbilitySearchParameter;

/**
*
* @author rizkykojek
*/
@Repository(value = "specificationAbilityDao")
@Lazy
public class SpecificationAbilityDaoImpl extends IDAOImpl<SpecificationAbility> implements SpecificationAbilityDao {

	@Override
	public Class<SpecificationAbility> getEntityClass() {
		return SpecificationAbility.class;
	}

	@Override
	public List<SpecificationAbility> getByParam(SpecificationAbilitySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(SpecificationAbilitySearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(SpecificationAbilitySearchParameter parameter, Criteria criteria) {
		if (parameter.getName() != null) {
        	criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
		if (parameter.getOptionAbility() != null) {
        	criteria.add(Restrictions.like("optionAbility", parameter.getOptionAbility(), MatchMode.ANYWHERE));
        }
		if (parameter.getScaleValue() != null) {
        	criteria.add(Restrictions.like("scaleValue", parameter.getScaleValue(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
	}

	@Override
	public Long getTotalByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByNameAndNotId(String name, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		criteria.add(Restrictions.ne("id", id));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
