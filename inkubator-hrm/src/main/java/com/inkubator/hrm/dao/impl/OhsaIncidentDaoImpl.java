/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OhsaIncidentDao;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.web.search.OhsaIncidentSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "ohsaIncidentDao")
@Lazy
public class OhsaIncidentDaoImpl extends IDAOImpl<OhsaIncident> implements OhsaIncidentDao {

    @Override
    public Class<OhsaIncident> getEntityClass() {
        return OhsaIncident.class;
    }

    @Override
    public List<OhsaIncident> getByParam(OhsaIncidentSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//	criteria.setFetchMode("ohsaCategory", FetchMode.JOIN);               
        doSearchByParam(parameter, criteria);
        
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);       
        
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(OhsaIncidentSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(OhsaIncidentSearchParameter parameter, Criteria criteria) {
        
             criteria.createAlias("ohsaCategory", "ohsaCategory", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(parameter.getKategori())) {
            criteria.add(Restrictions.like("ohsaCategory.name", parameter.getKategori(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getSubjek())) {        	
            criteria.add(Restrictions.like("subject", parameter.getSubjek(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getLokasi())) {        	
            criteria.add(Restrictions.like("location", parameter.getLokasi(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public OhsaIncident getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("ohsaCategory", FetchMode.JOIN);       
        criteria.add(Restrictions.eq("id", id));
        return (OhsaIncident) criteria.uniqueResult();
    }
}
