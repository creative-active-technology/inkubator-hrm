/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.IpPermitDao;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.web.search.IpPermitSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "ipPermitDao")
@Lazy
public class IpPermitDaoImpl extends IDAOImpl<IpPermit> implements IpPermitDao {

    @Override
    public Class<IpPermit> getEntityClass() {
        return IpPermit.class;
    }

    @Override
    public List<IpPermit> getByParam(IpPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMaritalStatusByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalIpPermitByParam(IpPermitSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMaritalStatusByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getByIpPermitLocation(String location) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("lokasi", location, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchMaritalStatusByParam(IpPermitSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getLokasi() != null) {
            criteria.add(Restrictions.like("lokasi", searchParameter.getLokasi(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<IpPermit> getByIpHeader(int ipHeader) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("fromAddress1", ipHeader));
        return criteria.list();
    }

}
