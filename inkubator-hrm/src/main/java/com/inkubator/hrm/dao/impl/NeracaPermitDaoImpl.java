/*
 <<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.NeracaPermitDao;
import com.inkubator.hrm.entity.NeracaPermit;
import com.inkubator.hrm.web.search.NeracaPermitSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
 * @author Taufik
 */
@Repository(value = "neracaPermitDao")
@Lazy
public class NeracaPermitDaoImpl extends IDAOImpl<NeracaPermit> implements NeracaPermitDao {

    @Override
    public Class<NeracaPermit> getEntityClass() {
        return NeracaPermit.class;
    }

    @Override
    public List<NeracaPermit> getByParam(NeracaPermitSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("permitDistribution", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.empData", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.empData.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.permitClassification", FetchMode.JOIN);
        doSearch(searchParameter, criteria);
//        criteria.createAlias("ld.empData", "empData", JoinType.INNER_JOIN);
        
        criteria.addOrder(Order.asc("permitClassification.name"));
        criteria.addOrder(order);
//        criteria.addOrder(Order.asc("ed.nik"));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalNeracaPermitByParam(NeracaPermitSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public NeracaPermit getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("permitDistribution", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.empData", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitDistribution.permitClassification", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (NeracaPermit) criteria.uniqueResult();
    }

    private void doSearch(NeracaPermitSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("permitDistribution", "permitDistribution", JoinType.INNER_JOIN);
        criteria.createAlias("permitDistribution.permitClassification", "permitClassification", JoinType.INNER_JOIN);
        criteria.createAlias("permitDistribution.empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("permitDistribution.empData.jabatanByJabatanId","jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        
        if (searchParameter.getEmpData() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmpData(), MatchMode.START));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(searchParameter.getPermit())) {
            criteria.createAlias("permitDistribution.permit", "permit", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("permit.name", searchParameter.getPermit(), MatchMode.START));
        }
        if(StringUtils.isNotEmpty(searchParameter.getJabatanName())){
        	criteria.add(Restrictions.like("jabatanByJabatanId.name", searchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getNik() != null) {
            
            criteria.add(Restrictions.like("empData.nik", searchParameter.getNik(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public void saveBacth(List<NeracaPermit> data) {
        int counter = 0;
        for (NeracaPermit neracaPermit : data) {
            getCurrentSession().save(neracaPermit);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }
}
