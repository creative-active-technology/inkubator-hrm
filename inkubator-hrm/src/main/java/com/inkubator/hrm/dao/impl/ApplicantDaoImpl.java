package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApplicantDao;
import com.inkubator.hrm.entity.Applicant;
import com.inkubator.hrm.web.search.ApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "applicantDao")
@Lazy
public class ApplicantDaoImpl extends IDAOImpl<Applicant>implements ApplicantDao {

	@Override
	public Class<Applicant> getEntityClass() {
		
		return Applicant.class;
	}

	@Override
	public List<Applicant> getByParam(ApplicantSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchByParam(parameter, criteria);
		criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(ApplicantSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(ApplicantSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
		criteria.createAlias("bioData.city", "city", JoinType.INNER_JOIN);

        if (StringUtils.isNotEmpty(parameter.getName())) {  
        	Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(parameter.getEmail())) {
        	criteria.add(Restrictions.like("bioData.personalEmail", parameter.getEmail(),MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCityName())) {
        	criteria.add(Restrictions.like("cityName", parameter.getCityName(),MatchMode.ANYWHERE));
        }
    }

}
