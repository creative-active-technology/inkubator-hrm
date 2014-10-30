package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HospitalDao;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.web.search.HospitalSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
@Repository(value = "hospitalDao")
@Lazy
public class HospitalDaoImpl extends IDAOImpl<Hospital> implements HospitalDao {

    @Override
    public Class<Hospital> getEntityClass() {
        return Hospital.class;
    }

    @Override
    public List<Hospital> getByParam(HospitalSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchHospitalByParam(parameter, criteria);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalHospitalByParam(HospitalSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchHospitalByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchHospitalByParam(HospitalSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Hospital getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        return (Hospital) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByPhone(String phone) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("phone", phone));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPhoneAndNotId(String phone, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("phone", phone));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
