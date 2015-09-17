/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.KlasifikasiKerjaLevelDao;
import com.inkubator.hrm.entity.KlasifikasiKerjaLevel;
import com.inkubator.hrm.web.search.KlasifikasiKerjaLevelSearchParameter;
import java.util.List;
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
 * @author EKA
 */
@Repository(value = "klasifikasiKerjaLevelDao")
@Lazy
public class KlasifikasiKerjaLevelDaoImpl extends IDAOImpl<KlasifikasiKerjaLevel> implements KlasifikasiKerjaLevelDao{

    @Override
    public Class<KlasifikasiKerjaLevel> getEntityClass() {
        return KlasifikasiKerjaLevel.class;
    }

    @Override
    public List<KlasifikasiKerjaLevel> getByParam(KlasifikasiKerjaLevelSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.createAlias("klasifikasiKerja", "klasifikasiKerja", JoinType.INNER_JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalKlasifikasiKerjaLevelByParam(KlasifikasiKerjaLevelSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

//    @Override
//    public KlasifikasiKerjaLevel getKlasifKerjaNameByKlasifKerjaLevelId(Long id) {
//        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//        criteria.add(Restrictions.eq("id", id));
//        criteria.setFetchMode("klasifikasiKerja", FetchMode.JOIN);
//        return (KlasifikasiKerjaLevel) criteria.uniqueResult();
//    }
    
    private void doSearchByParam(KlasifikasiKerjaLevelSearchParameter searchParameter, Criteria criteria){
        if(searchParameter.getName() != null){
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if(searchParameter.getCode() != null){
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
    @Override
    public KlasifikasiKerjaLevel getEntityWithDetail(Long id){
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("klasifikasiKerja", FetchMode.JOIN);
        criteria.setFetchMode("klasifikasiKerja.golonganJabatanByStrataAtasGoljabId", FetchMode.JOIN);
        criteria.setFetchMode("klasifikasiKerja.golonganJabatanByStrataBawahGoljabId", FetchMode.JOIN);
        criteria.setFetchMode("klasifikasiKerja.golonganJabatanByStrataAtasGoljabId.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("klasifikasiKerja.golonganJabatanByStrataBawahGoljabId.pangkat", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (KlasifikasiKerjaLevel) criteria.uniqueResult();
    }
}
