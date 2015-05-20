/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancySelectionDao;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import com.inkubator.hrm.web.search.RecruitVacancySelectionSearchParameter;
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
@Repository(value = "recruitVacancySelectionDao")
@Lazy
public class RecruitVacancySelectionDaoImpl extends IDAOImpl<RecruitVacancySelection> implements RecruitVacancySelectionDao{

    @Override
    public Class<RecruitVacancySelection> getEntityClass() {
        return RecruitVacancySelection.class;
    }

    @Override
    public List<RecruitVacancySelection> getByParam(RecruitVacancySelectionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(RecruitVacancySelectionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(RecruitVacancySelectionSearchParameter searchParameter, Criteria criteria) { 
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
}
