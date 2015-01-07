/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.sms.gateway.dao.ModemDefinitionDao;
import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "modemDefinitionDao")
@Lazy
public class ModemDefinitionDaoImpl extends IDAOImpl<ModemDefinition> implements ModemDefinitionDao {

    @Override
    public Class<ModemDefinition> getEntityClass() {
        return ModemDefinition.class;
    }

    @Override
    public Long getTotalByModemId(String modemId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("modemId", modemId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public long getTotalByModemIdAndNotId(String modemId, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("modemId", modemId, MatchMode.ANYWHERE));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<ModemDefinition> getAllByFullText(String param) {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder mythQB = searchFactory.buildQueryBuilder().forEntity(getEntityClass()).get();
        Query luceneQuery;
        if (param != null && !param.equalsIgnoreCase("")) {
            String parameter=param+"*";
            System.out.println(" nilai paramnya "+parameter);
            luceneQuery = mythQB.keyword().onField("model")
                    .andField("manufacture")
                    .andField("comId")
                    .andField("pinNumber")
                    .andField("smscNumber")
                    .andField("pricePerSms")
                    .andField("modemId")
                    .andField("currentValue")
                    .andField("baudRate")
                    .matching(parameter).createQuery();
        } else {
            luceneQuery = mythQB.all().createQuery();
        }
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, getEntityClass());
        return fullTextQuery.list();
    }

    @Override
    public ModemDefinition getByFullText(Long id) {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder mythQB = searchFactory.buildQueryBuilder().forEntity(getEntityClass()).get();
        Query luceneQuery = mythQB.keyword().onField("id").matching(id).createQuery();
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, ModemDefinition.class);
        return (ModemDefinition) fullTextQuery.uniqueResult();
    }

}
