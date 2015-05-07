/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioRelasiPerusahaanDao;
import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.web.search.BioRelasiPerusahaanSearchParameter;
import java.util.List;
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
@Repository(value = "bioRelasiPerusahaanDao")
@Lazy
public class BioRelasiPerusahaanDaoImpl extends IDAOImpl<BioRelasiPerusahaan> implements BioRelasiPerusahaanDao {

    @Override
    public Class<BioRelasiPerusahaan> getEntityClass() {
        return BioRelasiPerusahaan.class;
    }

    @Override
    public List<BioRelasiPerusahaan> getByParam(BioRelasiPerusahaanSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(BioRelasiPerusahaanSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<BioRelasiPerusahaan> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        return criteria.list();
    }
    
    public void doSearchByParam(BioRelasiPerusahaanSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", searchParameter.getName(), MatchMode.START));
            disjunction.add(Restrictions.like("bio.lastName", searchParameter.getName(), MatchMode.START));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public BioRelasiPerusahaan getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        return (BioRelasiPerusahaan) criteria.uniqueResult();
    }
}
