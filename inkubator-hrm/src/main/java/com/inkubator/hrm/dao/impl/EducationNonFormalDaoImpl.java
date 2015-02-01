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
import com.inkubator.hrm.dao.EducationNonFormalDao;
import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.web.search.EducationNonFormalSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "educationNonFormalDao")
@Lazy
public class EducationNonFormalDaoImpl extends IDAOImpl<EducationNonFormal> implements EducationNonFormalDao {

    @Override
    public Class<EducationNonFormal> getEntityClass() {
        return EducationNonFormal.class;
    }

    @Override
    public List<EducationNonFormal> getByParam(EducationNonFormalSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
    
    @Override
    public Long getTotalByParam(EducationNonFormalSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(EducationNonFormalSearchParameter parameter, Criteria criteria) {
        
        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (parameter.getCode() != null){
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public EducationNonFormal getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("city", FetchMode.JOIN);
		criteria.setFetchMode("city.province", FetchMode.JOIN);
		criteria.setFetchMode("city.province.country", FetchMode.JOIN);
		return (EducationNonFormal) criteria.uniqueResult();		
	}

}
