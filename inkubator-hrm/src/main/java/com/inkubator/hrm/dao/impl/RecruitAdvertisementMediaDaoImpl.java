/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitAdvertisementMediaDao;
import com.inkubator.hrm.entity.RecruitAdvertisementMedia;
import com.inkubator.hrm.web.search.RecruitAdvertisementMediaSearchParameter;
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
 * @author Deni
 */
@Repository(value = "recruitAdvertisementMediaDao")
@Lazy
public class RecruitAdvertisementMediaDaoImpl extends IDAOImpl<RecruitAdvertisementMedia> implements RecruitAdvertisementMediaDao {

    @Override
    public Class<RecruitAdvertisementMedia> getEntityClass() {
        return RecruitAdvertisementMedia.class;
    }

    @Override
    public List<RecruitAdvertisementMedia> getByParam(RecruitAdvertisementMediaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(RecruitAdvertisementMediaSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public RecruitAdvertisementMedia getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("recruitAdvertisementCategory", FetchMode.JOIN);
        return (RecruitAdvertisementMedia) criteria.uniqueResult();
    }
    
    private void doSearchByParam(RecruitAdvertisementMediaSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        } 
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }

}
