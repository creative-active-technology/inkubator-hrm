/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;
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
@Repository(value = "wtPeriodeDao")
@Lazy
public class WtPeriodeDaoImpl extends IDAOImpl<WtPeriode> implements WtPeriodeDao {

    @Override
    public Class<WtPeriode> getEntityClass() {
        return WtPeriode.class;
    }

    @Override
    public List<WtPeriode> getByParam(WtPeriodeSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtPeriodeByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalWtPeriodeByParam(WtPeriodeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtPeriodeByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchWtPeriodeByParam(WtPeriodeSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getTahun() != null) {
            criteria.add(Restrictions.like("tahun", searchParameter.getTahun(), MatchMode.START));
        }

        if (searchParameter.getBulan() != null && searchParameter.getBulan() != 0) {
            criteria.add(Restrictions.eq("bulan", searchParameter.getBulan()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public WtPeriode getEntityByStatusActive() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("payrollType", "Active"));
        return (WtPeriode) criteria.uniqueResult();
    }

    @Override
    public WtPeriode getEntityAbsenByStatusActive() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("absen", "Active"));
        return (WtPeriode) criteria.uniqueResult();
    }
}
