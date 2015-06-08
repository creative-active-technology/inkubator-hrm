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
import com.inkubator.hrm.web.model.WtPeriodEmpViewModel;
import com.inkubator.hrm.web.search.WtPeriodeEmpSearchParameter;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
    public WtPeriode getEntityByMonthAndYear(Integer month, String year) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bulan", month));
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

    @Override
    public WtPeriode getEntityByDateBetween(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.le("fromPeriode", date));
        criteria.add(Restrictions.ge("untilPeriode", date));
        return (WtPeriode) criteria.uniqueResult();
    }

    @Override
    public List<WtPeriodEmpViewModel> getListWtPeriodEmpByParam(WtPeriodeEmpSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        final StringBuilder query = new StringBuilder("select wtPeriode.id AS wtPeriodId, ");
        query.append(" wtPeriode.from_periode AS fromPeriode,");
        query.append(" wtPeriode.until_periode AS untilPeriode,");
        query.append(" COUNT(empData.nik) AS totalEmpData,");
        query.append(" COUNT(DISTINCT (empData.shift_group_id)) AS totalWorkingGroup,");
        query.append(" wtPeriode.absen as status ");
        query.append(" FROM wt_periode wtPeriode ");
        query.append(" INNER JOIN emp_data empData ON empData.join_date < wtPeriode.until_periode  ");
        query.append(" GROUP BY  wtPeriode.id ");
        query.append(" LIMIT  " + firstResult + "," + maxResults);

        return getCurrentSession().createSQLQuery(query.toString())
                //                    .setParameterList("idDept", departementId)
                //                    .setParameterList("idEdu", educationId)
                // .setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(WtPeriodEmpViewModel.class))
                .list();
    }

    @Override
    public Long getTotalListWtPeriodEmpByParam(WtPeriodeEmpSearchParameter searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT wtPeriode.id AS wtPeriodId, ");
        query.append(" wtPeriode.from_periode AS fromPeriode,");
        query.append(" wtPeriode.until_periode AS untilPeriode,");
        query.append(" COUNT(empData.nik) AS totalEmpData,");
        query.append(" COUNT(DISTINCT (empData.shift_group_id)) AS totalWorkingGroup,");
        query.append(" wtPeriode.absen as status ");
        query.append(" FROM wt_periode wtPeriode ");
        query.append(" INNER JOIN emp_data empData ON empData.join_date < wtPeriode.until_periode  ");
        query.append(" GROUP BY  wtPeriode.id) as jumlahRow ");

        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public WtPeriodEmpViewModel getWtPeriodEmpByWtPeriodId(Long wtPeriodId) {
        final StringBuilder query = new StringBuilder("select wtPeriode.id AS wtPeriodId, ");
        query.append(" wtPeriode.from_periode AS fromPeriode,");
        query.append(" wtPeriode.until_periode AS untilPeriode,");
        query.append(" COUNT(empData.nik) AS totalEmpData,");
        query.append(" COUNT(DISTINCT (empData.shift_group_id)) AS totalWorkingGroup,");
        query.append(" wtPeriode.absen as status ");
        query.append(" FROM wt_periode wtPeriode ");
        query.append(" INNER JOIN emp_data empData ON empData.join_date < wtPeriode.until_periode  ");       
        query.append(" WHERE  wtPeriode.id = " + wtPeriodId);
        Query hbm = getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(WtPeriodEmpViewModel.class));
        return (WtPeriodEmpViewModel)hbm.uniqueResult();
    }
}
