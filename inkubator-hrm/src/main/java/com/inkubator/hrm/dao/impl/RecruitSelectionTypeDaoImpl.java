/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionTypeDao;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.web.search.RecruitSelectionTypeSearchParameter;
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
 * @author Deni
 */
@Repository(value = "recruitSelectionTypeDao")
@Lazy
public class RecruitSelectionTypeDaoImpl extends IDAOImpl<RecruitSelectionType> implements RecruitSelectionTypeDao {

    @Override
    public Class<RecruitSelectionType> getEntityClass() {
        return RecruitSelectionType.class;
    }

    @Override
    public List<RecruitSelectionType> getByParam(RecruitSelectionTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(RecruitSelectionTypeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(RecruitSelectionTypeSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        } 
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
}
