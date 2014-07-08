/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnitKerjaDao;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
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
 * @author deniarianto
 */
@Repository(value = "unitKerjaDao")
@Lazy
public class UnitKerjaDaoImpl extends IDAOImpl<UnitKerja> implements UnitKerjaDao{

    @Override
    public Class<UnitKerja> getEntityClass() {
        return UnitKerja.class;
    }

    @Override
    public List<UnitKerja> getByParam(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchUnitKerjaByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalUnitKerjaByParam(UnitKerjaSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchUnitKerjaByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getByUnitKerjaCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchUnitKerjaByParam(UnitKerjaSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        } 
        if(searchParameter.getCode() != null ){
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        }
        if(searchParameter.getLocation() != null ){
        	criteria.add(Restrictions.like("location", searchParameter.getLocation(), MatchMode.ANYWHERE));
        }
        if(searchParameter.getLocation() != null){
        
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<UnitKerja> getAllDataWithCity(UnitKerjaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchUnitKerjaByParam(searchParameter, criteria);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public UnitKerja getEntityByPkWithCity(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.ne("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        return (UnitKerja) criteria.uniqueResult();
    }
}
