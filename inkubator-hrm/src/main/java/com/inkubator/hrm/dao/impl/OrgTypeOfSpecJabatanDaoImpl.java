/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OrgTypeOfSpecJabatanDao;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatanId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
@Repository(value = "orgTypeOfSpecJabatanDao")
@Lazy
public class OrgTypeOfSpecJabatanDaoImpl extends IDAOImpl<OrgTypeOfSpecJabatan> implements OrgTypeOfSpecJabatanDao{

    @Override
    public Class<OrgTypeOfSpecJabatan> getEntityClass() {
        return OrgTypeOfSpecJabatan.class;
    }

    @Override
    public List<OrgTypeOfSpecJabatan> getAllDataByJabatanId(Long id, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("jabatan.id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("orgTypeOfSpecList", FetchMode.JOIN);
        
        criteria.createAlias("jabatan", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("orgTypeOfSpecList", "orgTypeOfSpecList", JoinType.INNER_JOIN);
        
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalDataByJabatanId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("jabatan.id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public OrgTypeOfSpecJabatan getEntityByPK(OrgTypeOfSpecJabatanId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("orgTypeOfSpecList", FetchMode.JOIN);
        return (OrgTypeOfSpecJabatan) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByPK(OrgTypeOfSpecJabatanId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
}
