/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OverTimeDistributionDao;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.entity.OverTimeDistributionId;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Disjunction;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "overTimeDistributionDao")
@Lazy
public class OverTimeDistributionDaoImpl extends IDAOImpl<OverTimeDistribution> implements OverTimeDistributionDao {

    @Override
    public Class<OverTimeDistribution> getEntityClass() {
        return OverTimeDistribution.class;
    }

    @Override
    public List<OverTimeDistribution> getByParamWithDetail(OverTimeDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        //criteria.setFetchMode("empData", FetchMode.JOIN);
        //criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        //criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalOverTimeDistributionByParam(OverTimeDistributionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public OverTimeDistribution getEntityByParamWithDetail(Long empId, Long overTimeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.eq("wtOverTime.id", overTimeId));
        return (OverTimeDistribution) criteria.uniqueResult();
    }

    @Override
    public List<OverTimeDistribution> getAllDataByIdWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        return criteria.list();
    }

    private void doSearch(OverTimeDistributionSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("wtOverTime", "wtOverTime", JoinType.INNER_JOIN);
        if (searchParameter.getEmpData() != null) {

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmpData(), MatchMode.START));
            criteria.add(disjunction);
        }
        if (searchParameter.getNik() != null) {            
            criteria.add(Restrictions.like("empData.nik", searchParameter.getNik(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getWtOverTime())) {
            
            criteria.add(Restrictions.like("wtOverTime.name", searchParameter.getWtOverTime(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public void saveBatch(List<OverTimeDistribution> data) {
        int counter = 0;
        for (OverTimeDistribution overTimeDistribution : data) {
            getCurrentSession().save(overTimeDistribution);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public OverTimeDistribution getById(OverTimeDistributionId object) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("id", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", object));
        return (OverTimeDistribution) criteria.uniqueResult();
    }

}
