package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.NationalityDao;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.web.search.NationalitySearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "nationalityDao")
@Lazy
public class NationalityDaoImpl extends IDAOImpl<Nationality> implements NationalityDao {

    @Override
    public Class<Nationality> getEntityClass() {
        return Nationality.class;
    }

    @Override
    public List<Nationality> getByParam(NationalitySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchNationalityByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalNationalityByParam(NationalitySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchNationalityByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchNationalityByParam(NationalitySearchParameter parameter, Criteria criteria) {
        if (parameter.getNationalityCode() != null) {
            criteria.add(Restrictions.like("nationalityCode", parameter.getNationalityCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getNationalityName()!= null) {
            criteria.add(Restrictions.like("nationalityName", parameter.getNationalityName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("nationalityCode", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("nationalityCode", code, MatchMode.ANYWHERE));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
