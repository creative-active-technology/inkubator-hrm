/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BatchStepExecutionDao;
import com.inkubator.hrm.entity.BatchStepExecution;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EKA
 */
@Repository(value = "batchStepExecutionDao")
@Lazy

public class BatchStepExecutionDaoImpl extends IDAOImpl<BatchStepExecution> implements BatchStepExecutionDao {

    @Override
    public Class<BatchStepExecution> getEntityClass() {
        return BatchStepExecution.class;
    }

    @Override
    public List<BatchStepExecution> getExitMessageByJobId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("batchJobExecution", "bs", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bs.jobExecutionId", id)); 
        criteria.setFetchMode("batchJobExecution", FetchMode.JOIN);
        criteria.setFetchMode("batchJobExecution.batchJobInstance", FetchMode.JOIN);
        
        
        return criteria.list();
    }

    @Override
    public Long getTotalExitMessageByParam(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("batchJobExecution", "bs", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bs.jobExecutionId", id)); 
        criteria.setFetchMode("batchJobInstance", FetchMode.JOIN);
        criteria.setFetchMode("batchJobExecution", FetchMode.JOIN);
        
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
