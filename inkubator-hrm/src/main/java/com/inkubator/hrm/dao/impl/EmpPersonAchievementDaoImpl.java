/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmpPersonAchievementDao;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.web.search.EmpPersonAchievementSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
@Repository(value = "empPersonAchievementDaoImpl")
@Lazy
public class EmpPersonAchievementDaoImpl extends IDAOImpl<EmpPersonAchievement> implements EmpPersonAchievementDao{

    @Override
    public Class<EmpPersonAchievement> getEntityClass() {
        return EmpPersonAchievement.class;
    }

    @Override
    public List<EmpPersonAchievement> getAllDataWithEmployee(EmpPersonAchievementSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpPersonAchievementByParam(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalEmpPersonAchievementByParam(EmpPersonAchievementSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpPersonAchievementByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public EmpPersonAchievement getEntityByPkWithEmployee(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (EmpPersonAchievement) criteria.uniqueResult();
    }
    
    private void doSearchEmpPersonAchievementByParam(EmpPersonAchievementSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getAchievementName()!= null) {
        	criteria.add(Restrictions.like("achievementName", searchParameter.getAchievementName(), MatchMode.ANYWHERE));
        } 
        if (StringUtils.isNotEmpty(searchParameter.getEmpData())) {
            criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
            criteria.createAlias("ed.bioData", "bd", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("bd.firstName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<EmpPersonAchievement> getAllDataWithEmployee() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return criteria.list();
    }
}
