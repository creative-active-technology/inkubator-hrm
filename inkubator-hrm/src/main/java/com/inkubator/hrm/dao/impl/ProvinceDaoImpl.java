package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ProvinceDao;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.web.search.ProvinceSearchParameter;
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
@Repository(value = "provinceDao")
@Lazy
public class ProvinceDaoImpl extends IDAOImpl<Province> implements ProvinceDao {

    @Override
    public Class<Province> getEntityClass() {
        return Province.class;
    }

    @Override
    public List<Province> getByParam(ProvinceSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchProvinceByParam(parameter, criteria);
        criteria.setFetchMode("country", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalProvinceByParam(ProvinceSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchProvinceByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchProvinceByParam(ProvinceSearchParameter parameter, Criteria criteria) {
        if (parameter.getProvinceCode() != null) {
            criteria.add(Restrictions.like("provinceCode", parameter.getProvinceCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getProvinceName() != null) {
            criteria.add(Restrictions.like("provinceName", parameter.getProvinceName(), MatchMode.ANYWHERE));
        }

        if (parameter.getCountryName() != null) {
            criteria.createAlias("country", "c", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("c.countryName", parameter.getCountryName(), MatchMode.ANYWHERE));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("provinceCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("provinceCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Province getProvinceByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("country", FetchMode.JOIN);
        return (Province) criteria.uniqueResult();
    }

    @Override
    public List<Province> getByCountryIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("country", "c", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("c.id", id));
        criteria.setFetchMode("country", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<Province> getByCountryId(Long countryId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("country.id", countryId));
        return criteria.list();
    }

    @Override
    public Long getTotalByPhoneCode(String phoneCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("provincePhoneCode", phoneCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPhoneCodeAndNotId(String phoneCode, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("provincePhoneCode", phoneCode));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
