/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TravelComponentCostRateDao;
import com.inkubator.hrm.entity.TravelComponentCostRate;
import com.inkubator.hrm.web.search.TravelComponentCostRateSearchParameter;
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
@Repository(value = "travelComponentCostRateDao")
@Lazy
public class TravelComponentCostRateDaoImpl extends IDAOImpl<TravelComponentCostRate> implements TravelComponentCostRateDao{

    @Override
    public Class<TravelComponentCostRate> getEntityClass() {
        return TravelComponentCostRate.class;
    }

    @Override
    public List<TravelComponentCostRate> getAllDataWithAllRelation(TravelComponentCostRateSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        System.out.println(order.toString() + " order");
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTravelComponentCostRateByParam(searchParameter, criteria);
//        criteria.setFetchMode("costCenter", FetchMode.JOIN);
//        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
//        criteria.setFetchMode("travelComponent", FetchMode.JOIN);
//        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalTravelComponentRateByParam(TravelComponentCostRateSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTravelComponentCostRateByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public TravelComponentCostRate getEntityByPkWithAllRelation(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("travelComponent", FetchMode.JOIN);
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        return (TravelComponentCostRate) criteria.uniqueResult();
    }
    
    private void doSearchTravelComponentCostRateByParam(TravelComponentCostRateSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getCode()!= null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        } 
        
            criteria.createAlias("costCenter", "costCenter", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getCostCenter())) {
            criteria.add(Restrictions.like("costCenter.name", searchParameter.getCostCenter(), MatchMode.START));
        }
        
            criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getGolonganJabatan())) {
            criteria.add(Restrictions.like("golonganJabatan.code", searchParameter.getGolonganJabatan(), MatchMode.START));
        }
        
            criteria.createAlias("travelComponent", "travelComponent", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getTravelComponent())) {
            criteria.add(Restrictions.like("travelComponent.name", searchParameter.getTravelComponent(), MatchMode.START));
        }
        
            criteria.createAlias("travelZone", "travelZone", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getTravelZone())) {
           
            criteria.add(Restrictions.like("travelZone.name", searchParameter.getTravelZone(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    /*
     * Do Search With Order
     */
    private void doSearchTravelComponentCostRateByParam(TravelComponentCostRateSearchParameter searchParameter, Criteria criteria, Order order) {
        if (searchParameter.getCode()!= null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        } 
        if (StringUtils.isNotEmpty(searchParameter.getCostCenter())) {
            criteria.createAlias("costCenter", "cc", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("cc.name", searchParameter.getCostCenter(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getGolonganJabatan()) || order.toString().contains("golonganJabatan.code")) {
            criteria.createAlias("golonganJabatan", "gj", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("golonganJabatan.code", searchParameter.getGolonganJabatan(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getTravelComponent()) || order.toString().contains("travelComponent.name")) {
            criteria.createAlias("travelComponent", "travelComponent", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("travelComponent.name", searchParameter.getTravelComponent(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getTravelZone()) || order.toString().contains("travelZone.name")) {
           
            criteria.createAlias("travelZone", "travelZone", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("travelZone.name", searchParameter.getTravelZone(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
    @Override
    public Long getByTravelComponentCostRateCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

	@Override
	public List<TravelComponentCostRate> getAllDataByGolJabatanIdAndTravelZoneId(Long golJabatanId, Long travelZoneId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("golonganJabatan.id", golJabatanId));
		criteria.add(Restrictions.eq("travelZone.id", travelZoneId));
		criteria.setFetchMode("costCenter", FetchMode.JOIN);
		criteria.setFetchMode("travelComponent", FetchMode.JOIN);
		return criteria.list();
		
	}
}
