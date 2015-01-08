/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.sms.gateway.dao.UserDao;
import com.inkubator.sms.gateway.entity.User;
import java.util.List;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.criterion.Order;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "userDao")
@Lazy
public class UserDaoImpl extends IDAOImpl<User> implements UserDao{
    private static final long serialVersionUID = -3838059330383642763L;

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public List<User> getAllByFullTextService(String parameter, int minResult, int maxResult, Order order) {
        System.out.println(parameter+"muahahahhahahha");
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
            luceneQuery = mythQB.keyword().onField("userId").boostedTo(3)
                    .andField("realName")
                    .andField("emailAddress")
                    .matching(parameter + "*").createQuery();
        } else {
            luceneQuery = mythQB.all().createQuery();
        }
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, getEntityClass());
        return fullTextQuery;
    }
}
