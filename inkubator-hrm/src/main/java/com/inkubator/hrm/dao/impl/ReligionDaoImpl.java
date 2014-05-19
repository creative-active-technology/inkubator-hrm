package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ReligionDao;
import com.inkubator.hrm.entity.Religion;

/**
*
* @author rizkykojek
*/
@Repository(value = "religionDao")
@Lazy
public class ReligionDaoImpl extends IDAOImpl<Religion> implements ReligionDao {

	@Override
	public Class<Religion> getEntityClass() {
		return Religion.class;
	}

	@Override
	public List<Religion> getByParam(String parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReligionByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalReligionByParam(String parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchReligionByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchReligionByParam(String parameter, Criteria criteria) {
		if (StringUtils.isNotEmpty(parameter)) {
        	criteria.add(Restrictions.like("name", parameter, MatchMode.ANYWHERE));
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
