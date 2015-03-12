/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewSchemaDao;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.web.search.LoanNewSchemaSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "loanNewSchemaDao")
@Lazy
public class LoanNewSchemaDaoImpl extends IDAOImpl<LoanNewSchema> implements LoanNewSchemaDao{

    @Override
    public Class<LoanNewSchema> getEntityClass() {
        return LoanNewSchema.class;
    }

    @Override
    public List<LoanNewSchema> getAllDataByParam(LoanNewSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
//        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalDataByParam(LoanNewSchemaSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    public void doSearchByParam(LoanNewSchemaSearchParameter searchParameter, Criteria criteria){
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("loanSchemaName", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("loanSchemaCode", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
