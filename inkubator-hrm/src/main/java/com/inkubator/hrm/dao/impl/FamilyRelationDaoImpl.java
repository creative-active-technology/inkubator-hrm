/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FamilyRelationDao;
import com.inkubator.hrm.entity.FamilyRelation;
import com.inkubator.hrm.web.search.FamilyRelationSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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
@Repository(value = "familyRelationDao")
@Lazy
public class FamilyRelationDaoImpl extends IDAOImpl<FamilyRelation> implements FamilyRelationDao {

    @Override
    public Class<FamilyRelation> getEntityClass() {
        return FamilyRelation.class;
    }

    @Override
    public List<FamilyRelation> getByParam(FamilyRelationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchFamilyRelationByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalFamilyRelationByParam(FamilyRelationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchFamilyRelationByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("relasiName", name));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByNameAndNotId(String name, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("relasiName", name));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchFamilyRelationByParam(FamilyRelationSearchParameter parameter, Criteria criteria) {
        if (parameter.getRelasiName() != null) {
            criteria.add(Restrictions.like("relasiName", parameter.getRelasiName(), MatchMode.ANYWHERE));
        }
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
