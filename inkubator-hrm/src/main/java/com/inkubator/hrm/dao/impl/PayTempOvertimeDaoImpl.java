/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempOvertimeDao;
import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;

/**
 *
 * @author deni
 */
@Repository(value = "payTempOvertimeDao")
@Lazy
public class PayTempOvertimeDaoImpl extends IDAOImpl<PayTempOvertime> implements PayTempOvertimeDao {

    @Override
    public Class<PayTempOvertime> getEntityClass() {
        return PayTempOvertime.class;
    }

    @Override
    public List<PayTempOvertime> getByParam(PayTempOvertimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(PayTempOvertimeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(PayTempOvertimeSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        if (searchParameter.getEmployeeName() != null) {
//            criteria.add(Restrictions.like("bioData.firstName", searchParameter.getEmployeeName(), MatchMode.ANYWHERE));
            criteria.add(Restrictions.ilike("bioData.combineName", searchParameter.getEmployeeName().toLowerCase(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getNim() != null) {
            criteria.add(Restrictions.like("empData.nik", searchParameter.getNim(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PayTempOvertime getAllDataByNik(String nik) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData");
        criteria.add(Restrictions.eq("empData.nik", nik));
        return (PayTempOvertime) criteria.uniqueResult();
    }

    @Override
    public PayTempOvertime getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (PayTempOvertime) criteria.uniqueResult();
    }

    @Override
    public void deleteAllData() {
        Query query = getCurrentSession().createQuery("delete from PayTempOvertime");
        query.executeUpdate();
    }

    @Override
    public PayTempOvertime getEntityByEmpDataId(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        return (PayTempOvertime) criteria.uniqueResult();
    }
}
