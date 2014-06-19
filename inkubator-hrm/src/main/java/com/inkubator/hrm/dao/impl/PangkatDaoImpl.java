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
import com.inkubator.hrm.dao.PangkatDao;
import com.inkubator.hrm.entity.Pangkat;
import com.inkubator.hrm.web.search.PangkatSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "pangkatDao")
@Lazy
public class PangkatDaoImpl extends IDAOImpl<Pangkat> implements PangkatDao {

    @Override
    public Class<Pangkat> getEntityClass() {
        return Pangkat.class;
    }

    @Override
    public List<Pangkat> getByParam(PangkatSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(PangkatSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(PangkatSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getPangkatName())) {
            criteria.add(Restrictions.like("pangkatName", parameter.getPangkatName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getPangkatCode())) {
            criteria.add(Restrictions.like("pangkatCode", parameter.getPangkatCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByPangkatName(String pangkatName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("pangkatName", pangkatName));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPangkatNameAndNotId(String pangkatName, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("pangkatName", pangkatName));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPangkatCode(String pangkatCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("pangkatCode", pangkatCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPangkatCodeAndNotId(String pangkatCode, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("pangkatCode", pangkatCode));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
