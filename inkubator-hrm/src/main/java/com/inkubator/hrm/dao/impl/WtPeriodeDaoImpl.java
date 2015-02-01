/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;

import java.util.Date;
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
    public WtPeriode getEntityByPayrollTypeActive() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("payrollType", HRMConstant.PERIODE_PAYROLL_ACTIVE));
        return (WtPeriode) criteria.uniqueResult();
    }

    @Override
    public WtPeriode getEntityByAbsentTypeActive() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("absen", HRMConstant.PERIODE_ABSEN_ACTIVE));
        return (WtPeriode) criteria.uniqueResult();
    }

    @Override
    public List<WtPeriode> getAllYears() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setProjection(Projections.distinct(Projections.property("tahun")));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public WtPeriode getEntityByMonthAndYear(String month, String year) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bulan", Integer.valueOf(month)));
        criteria.add(Restrictions.eq("tahun", year));
        return (WtPeriode) criteria.uniqueResult();
    }

    @Override
    public WtPeriode getEntityByFromPeriodeAndUntilPeriode(Date fromPeriode, Date untilPeriode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("fromPeriode", fromPeriode));
        criteria.add(Restrictions.eq("untilPeriode", untilPeriode));
        return (WtPeriode) criteria.uniqueResult();
    }
}
