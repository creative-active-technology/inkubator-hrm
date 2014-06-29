package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CountryDao;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.web.search.CountrySearchParameter;
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
@Repository(value = "countryDao")
@Lazy
public class CountryDaoImpl extends IDAOImpl<Country> implements CountryDao {

    @Override
    public Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    public List<Country> getByParam(CountrySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchCountryByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalCountryByParam(CountrySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchCountryByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchCountryByParam(CountrySearchParameter parameter, Criteria criteria) {
        if (parameter.getCountryCode() != null) {
            criteria.add(Restrictions.like("countryCode", parameter.getCountryCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getCountryName()!= null) {
            criteria.add(Restrictions.like("countryName", parameter.getCountryName(), MatchMode.ANYWHERE));
        }
        
        
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("countryCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public Long getTotalByPhoneCode(Integer code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("phoneCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("countryCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
