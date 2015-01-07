/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.sms.gateway.dao.SmsActivityDao;
import com.inkubator.sms.gateway.entity.SmsActivity;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import java.util.Date;
import java.util.List;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
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
@Repository(value = "smsActivityDao")
@Lazy
public class SmsActivityDaoImpl extends IDAOImpl<SmsActivity> implements SmsActivityDao {

    @Override
    public Class<SmsActivity> getEntityClass() {
        return SmsActivity.class;
    }

    @Override
    public List<SmsActivity> getListBySendDate(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("sendDate", new Date()));
        criteria.addOrder(Order.asc("sendDate"));
        criteria.addOrder(Order.desc("sendTime"));
        return criteria.list();
    }

    @Override
    public List<SmsActivity> getAllByLucenSendDate(Date date) {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(SmsActivity.class).get();
        org.apache.lucene.search.Query query = qb.keyword().onField("sendDate").matching(date).createQuery();
        org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, SmsActivity.class);
        List<SmsActivity> results = hibQuery.list();
        return results;

    }

    @Override
    public List<SmsActivity> getAllByFullTextService(String parameter, int minResult, int maxResult, Order order) {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        org.apache.lucene.search.Sort sort;
        if (order.isAscending()) {
            sort = new Sort(new SortField(order.getPropertyName(), SortField.STRING_VAL));
        } else {
            sort = new Sort(new SortField(order.getPropertyName(), SortField.STRING_VAL, true));
        }
        System.out.println("Nilai sorrt " + order.getPropertyName());
        FullTextQuery fullTextQuery1 = doSearchFullText(parameter, fullTextSession);
        fullTextQuery1.setFirstResult(minResult);
        fullTextQuery1.setMaxResults(maxResult);
        fullTextQuery1.setSort(sort);
        return fullTextQuery1.list();
    }

    @Override
    public Integer getTotalByFullTextService(String parameter) {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        return doSearchFullText(parameter, fullTextSession).getResultSize();
    }

    private FullTextQuery doSearchFullText(String parameter, FullTextSession fullTextSession) {
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder mythQB = searchFactory.buildQueryBuilder().forEntity(getEntityClass()).get();
        Query luceneQuery;
        if (parameter != null && !parameter.equalsIgnoreCase("")) {
            luceneQuery = mythQB.keyword().onField("fromSms").boostedTo(3)
                    .andField("destination")
                    .andField("contentSms")
                    .andField("priceSms")
                    .matching(parameter + "*").createQuery();
        } else {
            luceneQuery = mythQB.all().createQuery();
        }
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, getEntityClass());
        return fullTextQuery;
    }
}
