/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempOvertimeDao;
import com.inkubator.hrm.entity.PayTempOvertime;
import com.inkubator.hrm.web.search.PayTempOvertimeSearchParameter;
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
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
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
        if (searchParameter.getEmployeeName() != null) {
            criteria.createAlias("empData", "empData");
            criteria.createAlias("empData.bioData", "bioData");
            criteria.add(Restrictions.like("bioData.firstName", searchParameter.getEmployeeName(), MatchMode.START));
        }
        if (searchParameter.getNim() != null) {
            criteria.createAlias("empData", "empData");
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
        criteria.add(Restrictions.eq("id", id));
        return (PayTempOvertime) criteria.uniqueResult();
    }
}