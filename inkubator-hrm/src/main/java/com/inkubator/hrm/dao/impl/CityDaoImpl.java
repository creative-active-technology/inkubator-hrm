package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CityDao;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.web.search.CitySearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "cityDao")
@Lazy
public class CityDaoImpl extends IDAOImpl<City> implements CityDao {

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public List<City> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchCityByParam(parameter, criteria);
        criteria.setFetchMode("province", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalCityByParam(CitySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchCityByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchCityByParam(CitySearchParameter parameter, Criteria criteria) {
        if (parameter.getCityCode() != null) {
            criteria.add(Restrictions.like("cityCode", parameter.getCityCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getCityName()!= null) {
            criteria.add(Restrictions.like("cityName", parameter.getCityName(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getProvinceName()!= null) {
            criteria.createAlias("province", "p", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("p.provinceName", parameter.getProvinceName(), MatchMode.ANYWHERE));
        }
        
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("cityCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("cityCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public City getCityByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("province", FetchMode.JOIN);
        criteria.setFetchMode("province.country", FetchMode.JOIN);
        return (City) criteria.uniqueResult();
    }
    
    @Override
    public List<City> getByProvinceIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("province", "p", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("p.id", id));
        criteria.setFetchMode("province", FetchMode.JOIN);
        return criteria.list();
    }

}
