/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TerminationDao;
import com.inkubator.hrm.entity.Termination;
import com.inkubator.hrm.web.search.TerminationSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "terminationDao")
@Lazy
public class TerminationDaoImpl extends IDAOImpl<Termination> implements TerminationDao {

    @Override
    public Class<Termination> getEntityClass() {
        return Termination.class;
    }

    @Override
    public List<Termination> getAllDataWithAllRelation(TerminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTerminationByParam(searchParameter, criteria);
//        criteria.setFetchMode("empData", FetchMode.JOIN);
//        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("terminationType", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalTerminationByParam(TerminationSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTerminationByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Termination getEntityByPkWithAllRelation(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("terminationType", FetchMode.JOIN);
        return (Termination) criteria.uniqueResult();
    }

    @Override
    public Long getByTerminationCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchTerminationByParam(TerminationSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
        criteria.createAlias("ed.bioData", "bioData", JoinType.INNER_JOIN);

        criteria.createAlias("terminationType", "terminationType", JoinType.INNER_JOIN);
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getEmpData())) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("ed.nik", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmpData(), MatchMode.START));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(searchParameter.getTerminationType())) {
            criteria.add(Restrictions.like("terminationType.name", searchParameter.getTerminationType(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
