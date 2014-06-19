package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OccupationTypeDao;
import com.inkubator.hrm.entity.OccupationType;
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
@Repository(value = "occupationTypeDao")
@Lazy
public class OccupationTypeDaoImpl extends IDAOImpl<OccupationType> implements OccupationTypeDao {

    @Override
    public Class<OccupationType> getEntityClass() {
        return OccupationType.class;
    }

    @Override
    public List<OccupationType> getByParam(String parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchOccupationTypeByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalOccupationTypeByParam(String parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchOccupationTypeByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchOccupationTypeByParam(String parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter)) {
            criteria.add(Restrictions.like("occupationTypeName", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("occupationTypeName", name));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByNameAndNotId(String name, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("occupationTypeName", name));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
