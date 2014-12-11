/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BatchJobExecutionDao;
import com.inkubator.hrm.entity.BatchJobExecution;
import com.inkubator.hrm.web.search.BatchJobExecutionSearchParameter;
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

@Repository(value = "batchJobExecutionDao")
@Lazy
        
public class BatchJobExecutionDaoImpl extends IDAOImpl<BatchJobExecution> implements BatchJobExecutionDao{

    @Override
    public Class<BatchJobExecution> getEntityClass() {
        return BatchJobExecution.class;
    }

    @Override
    public List<BatchJobExecution> getByParam(BatchJobExecutionSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        if(parameter.getJobName() != null){
//            System.out.println("testtesttest");
            criteria.createAlias("batchJobInstance", "bj", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("bj.jobName", parameter.getJobName(), MatchMode.ANYWHERE));
        }
        if(parameter.getStatus() != null){
            criteria.add(Restrictions.like("BatchJobExecution", parameter.getStatus()));
        }
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.setFetchMode("batchJobInstance", FetchMode.JOIN);
        return criteria.list();
    } 

    @Override
    public Long getTotalByParam(BatchJobExecutionSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        if(parameter.getJobName() != null){
            criteria.createAlias("batchJobInstance", "bj", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("bj.jobName", parameter.getJobName(), MatchMode.ANYWHERE));
        }
        if(parameter.getStatus() != null){
            criteria.add(Restrictions.like("BatchJobExecution", parameter.getStatus()));
        }
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
