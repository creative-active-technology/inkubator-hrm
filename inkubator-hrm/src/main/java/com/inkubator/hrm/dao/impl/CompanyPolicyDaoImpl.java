package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CompanyPolicyDao;
import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.web.search.CompanyPolicySearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "companyPolicyDao")
@Lazy
public class CompanyPolicyDaoImpl extends IDAOImpl<CompanyPolicy> implements CompanyPolicyDao {

    @Override
    public Class<CompanyPolicy> getEntityClass() {
        return CompanyPolicy.class;
    }

    @Override
    public List<CompanyPolicy> getByParam(CompanyPolicySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
    
    @Override
    public Long getTotalByParam(CompanyPolicySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(CompanyPolicySearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getSubject())) {
            criteria.add(Restrictions.like("subjectTitle", parameter.getSubject(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getDepartment())) {
        	criteria.createAlias("department", "department", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("department.departmentName", parameter.getDepartment(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public CompanyPolicy getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		return (CompanyPolicy) criteria.uniqueResult();		
	}

}
