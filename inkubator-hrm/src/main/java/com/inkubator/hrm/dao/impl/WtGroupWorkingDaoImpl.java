/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.web.search.WtGroupWorkingSearchParameter;
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
 * @author Deni Husni FR
 */
@Repository(value = "wtGroupWorkingDao")
@Lazy
public class WtGroupWorkingDaoImpl extends IDAOImpl<WtGroupWorking> implements WtGroupWorkingDao {

    @Override
    public Class<WtGroupWorking> getEntityClass() {
        return WtGroupWorking.class;
    }

    @Override
    public List<WtGroupWorking> getByParam(WtGroupWorkingSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtGroupWorkingByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalWtGroupWorkingByParam(WtGroupWorkingSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtGroupWorkingByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchWtGroupWorkingByParam(WtGroupWorkingSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getKode() != null && !searchParameter.getKode().isEmpty()) {
            criteria.add(Restrictions.like("code", searchParameter.getKode(), MatchMode.ANYWHERE));
        }

        if (searchParameter.getName() != null && !searchParameter.getName().isEmpty()) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        }

    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public WtGroupWorking getByPKIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("wtScheduleShifts", FetchMode.JOIN);
        return (WtGroupWorking) criteria.uniqueResult();
    }

    @Override
    public WtGroupWorking getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (WtGroupWorking) criteria.uniqueResult();
    }

}
