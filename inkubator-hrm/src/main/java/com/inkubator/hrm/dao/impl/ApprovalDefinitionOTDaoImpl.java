/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionOTDao;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository
@Lazy
public class ApprovalDefinitionOTDaoImpl extends IDAOImpl<ApprovalDefinitionOT> implements ApprovalDefinitionOTDao {

    @Override
    public Class<ApprovalDefinitionOT> getEntityClass() {
        return ApprovalDefinitionOT.class;
    }

    @Override
    public List<ApprovalDefinitionOT> getByOverTimeId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        criteria.createAlias("approvalDefinition", "approvalDefinition", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("wtOverTime.id", id));
        criteria.addOrder(Order.asc("approvalDefinition.sequence"));
        return criteria.list();
    }
    
}
