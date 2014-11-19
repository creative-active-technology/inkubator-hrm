/*
<<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.web.search.NeracaCutiSearchParameter;
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
/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "neracaCutiDao")
@Lazy
public class NeracaCutiDaoImpl extends IDAOImpl<NeracaCuti> implements NeracaCutiDao{

    @Override
    public Class<NeracaCuti> getEntityClass() {
        return NeracaCuti.class;
    }

    @Override
    public List<NeracaCuti> getByParam(NeracaCutiSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("leaveDistribution", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.leave", FetchMode.JOIN);
        doSearch(searchParameter, criteria);
//        criteria.createAlias("ld.empData", "empData", JoinType.INNER_JOIN);
//        criteria.createAlias("ld.leave", "leave", JoinType.INNER_JOIN);
        criteria.addOrder(Order.asc("l.name"));
        criteria.addOrder(order);
        criteria.addOrder(Order.asc("ed.nik"));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalNeracaCutiByParam(NeracaCutiSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public NeracaCuti getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("leaveDistribution", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.leave", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (NeracaCuti) criteria.uniqueResult();
    }
    
    private void doSearch(NeracaCutiSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("leaveDistribution", "ld", JoinType.INNER_JOIN);
        criteria.createAlias("ld.empData", "ed", JoinType.INNER_JOIN);
        criteria.createAlias("ed.bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("ld.leave", "l", JoinType.INNER_JOIN);
        if (searchParameter.getEmpData()!= null) {

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(searchParameter.getLeave())) {
            
            criteria.add(Restrictions.like("l.name", searchParameter.getLeave(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getNik()!= null) {
            criteria.add(Restrictions.like("ed.nik", searchParameter.getNik(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public void saveBacth(List<NeracaCuti> data) {
       int counter = 0;
        for (NeracaCuti neracaCuti : data) {
            getCurrentSession().save(neracaCuti);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }
}
