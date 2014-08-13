/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "bioDataDao")
@Lazy
public class BioDataDaoImpl extends IDAOImpl<BioData> implements BioDataDao {

    @Override
    public Class<BioData> getEntityClass() {
        return BioData.class;
    }

    @Override
    public List<BioData> getByParam(BioDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBioDataByParam(parameter, criteria);
        criteria.setFetchMode("religion", FetchMode.JOIN);
        criteria.setFetchMode("maritalStatus", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(BioDataSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBioDataByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchBioDataByParam(BioDataSearchParameter parameter, Criteria criteria) {
        if (parameter.getName() != null) {
//            criteria.add(Restrictions.like("firstName", parameter.getParameter(), MatchMode.ANYWHERE));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("firstName", parameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("lastName", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }

        if (parameter.getEmailAddress() != null) {
            criteria.add(Restrictions.like("personalEmail", parameter.getEmailAddress(), MatchMode.ANYWHERE));
        }
        if (parameter.getNickName() != null) {
            criteria.add(Restrictions.like("nickname", parameter.getNickName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));

    }

    @Override
    public List<BioData> getEntityByPKWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("race", FetchMode.JOIN);
        criteria.setFetchMode("religion", FetchMode.JOIN);
        criteria.setFetchMode("nationality", FetchMode.JOIN);
        return criteria.list();
    }

}
