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
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.entity.EducationLevel;

/**
*
* @author rizkykojek
*/
@Repository(value = "educationLevelDao")
@Lazy
public class EducationLevelDaoImpl extends IDAOImpl<EducationLevel> implements EducationLevelDao {

	@Override
	public Class<EducationLevel> getEntityClass() {
		return EducationLevel.class;
	}

	@Override
	public List<EducationLevel> getByParam(String parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(String parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(String parameter, Criteria criteria) {
		if (StringUtils.isNotEmpty(parameter)) {
        	criteria.add(Restrictions.like("name", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
	}

}
