/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.sms.gateway.dao.TaskDefinitionDao;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import java.util.List;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
 * @author Deni Husni FR
 */
@Repository(value = "taskDefinitionDao")
@Lazy
public class TaskDefinitionDaoImpl extends IDAOImpl<TaskDefinition> implements TaskDefinitionDao {

    @Override
    public Class<TaskDefinition> getEntityClass() {
        return TaskDefinition.class;
    }

    @Override
    public List<TaskDefinition> getAllByFullTextService(String parameter, int minResult, int maxResult, Order orde) {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        Criteria criteria = fullTextSession.createCriteria(TaskDefinition.class).setFetchMode("modemDefinition", FetchMode.JOIN);
        org.apache.lucene.search.Sort sort;
        if (orde.isAscending()) {
            sort = new Sort(new SortField(orde.getPropertyName(), SortField.STRING_VAL));
        } else {
            sort = new Sort(new SortField(orde.getPropertyName(), SortField.STRING_VAL, true));
        }
        System.out.println("Nilai sorrt " + orde.getPropertyName());
        FullTextQuery fullTextQuery1 = doSearchFullText(parameter, fullTextSession);
        fullTextQuery1.setCriteriaQuery(criteria);
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
            luceneQuery = mythQB.keyword().onField("name").boostedTo(3)
                    .andField("scheduleType")
                    .andField("isRepeatOnCondition")
                    .andField("smsContent")
                    .andField("modemDefinition.manufacture")
                    .andField("modemDefinition.comId")
                    .matching(parameter+"*").createQuery();
        } else {
            luceneQuery = mythQB.all().createQuery();
        }
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, TaskDefinition.class);
        return fullTextQuery;
    }

    @Override
    public TaskDefinition getByFullText(Long id) {
        System.out.println(" hahhahahah");
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        SearchFactory searchFactory = fullTextSession.getSearchFactory();
        QueryBuilder mythQB = searchFactory.buildQueryBuilder().forEntity(getEntityClass()).get();
        Query luceneQuery = mythQB.keyword().onField("id").matching(id).createQuery();
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, TaskDefinition.class);
        return (TaskDefinition) fullTextQuery.uniqueResult();
    }
}
