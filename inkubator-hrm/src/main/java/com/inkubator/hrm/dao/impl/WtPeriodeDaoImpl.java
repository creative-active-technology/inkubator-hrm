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
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.hrm.web.model.WtPeriodEmpViewModel;
import com.inkubator.hrm.web.search.WtAttendanceCalculationSearchParameter;
import com.inkubator.hrm.web.search.WtPeriodeEmpSearchParameter;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
        final StringBuilder fromQuery = new StringBuilder();
        fromQuery.append("select wtPeriode.id AS wtPeriodId, ");
        fromQuery.append("wtPeriode.from_periode AS fromPeriode,");
        fromQuery.append("wtPeriode.until_periode AS untilPeriode,");
        fromQuery.append("SUM(CASE WHEN tempAttendanceRealization.id IS NOT NULL THEN 1 ELSE 0 END) AS totalEmpDataFromTemporary, ");
        fromQuery.append("SUM(CASE WHEN logWtAttendanceRealization.id IS NOT NULL THEN 1 ELSE 0 END) AS totalEmpDataFromLogMonthEnd, ");
        fromQuery.append("COUNT(DISTINCT (tempAttendanceRealization.wt_group_working_id)) AS totalWorkingGroupFromTemporary, ");
        fromQuery.append("COUNT(DISTINCT (logWtAttendanceRealization.wt_group_working_id)) AS totalWorkingGroupFromLogMonthEnd, ");
        fromQuery.append("wtPeriode.absen as status ");
        fromQuery.append("FROM wt_periode wtPeriode ");
        fromQuery.append("LEFT JOIN log_wt_attendance_realization logWtAttendanceRealization ON logWtAttendanceRealization.wt_periode_id=wtPeriode.id ");
        fromQuery.append("LEFT JOIN temp_attendance_realization tempAttendanceRealization ON tempAttendanceRealization.wt_period_id=wtPeriode.id ");
        //filter by search param
        fromQuery.append(doSearchWtPeriodEmpByParam(searchParameter));
        fromQuery.append("GROUP BY  wtPeriode.id ");

        final StringBuilder query = new StringBuilder();
        query.append("select wtPeriodId, ");
        query.append("fromPeriode, ");
        query.append("untilPeriode, ");
        query.append("status, ");
        query.append("CASE WHEN totalEmpDataFromLogMonthEnd > 0 THEN totalEmpDataFromLogMonthEnd ELSE totalEmpDataFromTemporary END AS totalEmpData, ");
        query.append("CASE WHEN totalWorkingGroupFromLogMonthEnd > 0 THEN totalWorkingGroupFromLogMonthEnd ELSE totalWorkingGroupFromTemporary END AS totalWorkingGroup ");
        query.append("FROM (" + fromQuery.toString() + ") AS xx ");
        query.append("ORDER BY  " + order);

        Query hbm = getCurrentSession().createSQLQuery(query.toString()).setFirstResult(firstResult).setMaxResults(maxResults);
        hbm = this.setValueQueryWtPeriodEmpByParam(hbm, searchParameter);

        return hbm.setResultTransformer(Transformers.aliasToBean(WtPeriodEmpViewModel.class)).list();
    }

    @Override
    public Long getTotalListWtPeriodEmpByParam(WtPeriodeEmpSearchParameter searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM wt_periode wtPeriode ");

        //filter by search param
        query.append(doSearchWtPeriodEmpByParam(searchParameter));

        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        hbm = this.setValueQueryWtPeriodEmpByParam(hbm, searchParameter);

        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String doSearchWtPeriodEmpByParam(WtPeriodeEmpSearchParameter searchParameter) {
        StringBuilder query = new StringBuilder();

        if (searchParameter.getStartPeriod() != null) {
            query.append(" WHERE wtPeriode.from_periode = :startPeriod ");
        }
        if (searchParameter.getEndPeriod() != null) {
            query.append(" WHERE wtPeriode.until_periode = :endPeriod ");
        }

        return query.toString();
    }

    private Query setValueQueryWtPeriodEmpByParam(Query hbm, WtPeriodeEmpSearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "startPeriod")) {
                hbm.setParameter("startPeriod", parameter.getStartPeriod());
            } else if (StringUtils.equals(param, "endPeriod")) {
                hbm.setParameter("endPeriod", parameter.getEndPeriod());
            }
        }
        return hbm;
    }

    @Override
    public WtPeriodEmpViewModel getWtPeriodEmpByWtPeriodId(Long wtPeriodId) {
        final StringBuilder fromQuery = new StringBuilder();
        fromQuery.append("select wtPeriode.id AS wtPeriodId, ");
        fromQuery.append("wtPeriode.from_periode AS fromPeriode,");
        fromQuery.append("wtPeriode.until_periode AS untilPeriode,");
        fromQuery.append("SUM(CASE WHEN tempAttendanceRealization.id IS NOT NULL THEN 1 ELSE 0 END) AS totalEmpDataFromTemporary, ");
        fromQuery.append("SUM(CASE WHEN logWtAttendanceRealization.id IS NOT NULL THEN 1 ELSE 0 END) AS totalEmpDataFromLogMonthEnd, ");
        fromQuery.append("COUNT(DISTINCT (tempAttendanceRealization.wt_group_working_id)) AS totalWorkingGroupFromTemporary, ");
        fromQuery.append("COUNT(DISTINCT (logWtAttendanceRealization.wt_group_working_id)) AS totalWorkingGroupFromLogMonthEnd, ");
        fromQuery.append("wtPeriode.absen as status ");
        fromQuery.append("FROM wt_periode wtPeriode ");
        fromQuery.append("LEFT JOIN log_wt_attendance_realization logWtAttendanceRealization ON logWtAttendanceRealization.wt_periode_id=wtPeriode.id ");
        fromQuery.append("LEFT JOIN temp_attendance_realization tempAttendanceRealization ON tempAttendanceRealization.wt_period_id=wtPeriode.id ");
        fromQuery.append("WHERE wtPeriode.id = " + wtPeriodId + " ");
        fromQuery.append("GROUP BY  wtPeriode.id ");

        final StringBuilder query = new StringBuilder();
        query.append("select wtPeriodId, ");
        query.append("fromPeriode, ");
        query.append("untilPeriode, ");
        query.append("status, ");
        query.append("CASE WHEN totalEmpDataFromLogMonthEnd > 0 THEN totalEmpDataFromLogMonthEnd ELSE totalEmpDataFromTemporary END AS totalEmpData, ");
        query.append("CASE WHEN totalWorkingGroupFromLogMonthEnd > 0 THEN totalWorkingGroupFromLogMonthEnd ELSE totalWorkingGroupFromTemporary END AS totalWorkingGroup ");
        query.append("FROM (" + fromQuery.toString() + ") AS xx ");

        Query hbm = getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(WtPeriodEmpViewModel.class));
        return (WtPeriodEmpViewModel) hbm.uniqueResult();
    }

    @Override
    public List<WtPeriode> getAllWithStatusAbsen(String status) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("absen", status));
        criteria.addOrder(Order.desc("tahun"));
        criteria.addOrder(Order.asc("bulan"));

        return criteria.list();
    }
}
