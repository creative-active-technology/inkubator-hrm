/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewSchemaListOfTypeDao;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "loanNewSchemaListOfTypeDao")
@Lazy
public class LoanNewSchemaListOfTypeDaoImpl extends IDAOImpl<LoanNewSchemaListOfType> implements LoanNewSchemaListOfTypeDao {

    @Override
    public Class<LoanNewSchemaListOfType> getEntityClass() {
        return LoanNewSchemaListOfType.class;
    }

    @Override
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchema(Long loanNewSchema) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loanNewSchema", "loanNewSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchema));
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return (LoanNewSchemaListOfType) criteria.uniqueResult();
    }

    @Override
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (LoanNewSchemaListOfType) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByLoanTypeAndSchema(Long typeId, Long schemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanNewSchema.id", schemaId));
        criteria.add(Restrictions.eq("loanNewType.id", typeId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaIdAndLoanNewTypeIdWithDetail(Long loanNewSchemaId, Long loanNewTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchemaId));
        criteria.add(Restrictions.eq("loanNewType.id", loanNewTypeId));
        return (LoanNewSchemaListOfType) criteria.uniqueResult();
    }

    @Override
    public List<LoanNewSchemaListOfType> getAllDataByLoanSchemaId(Long loanSchemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loanNewSchema", "lns");
        criteria.add(Restrictions.eq("lns.id", loanSchemaId));
        criteria.addOrder(Order.desc("loanNewType"));
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchemaWhereStatusActive(Long loanNewSchema) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loanNewSchema", "loanNewSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchema));
        criteria.add(Restrictions.eq("isActive", Boolean.TRUE));
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalBySchemaAndTypeAndStatusActive(Long schemaId, Long typeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanNewSchema.id", schemaId));
        criteria.add(Restrictions.eq("loanNewType.id", typeId));
        criteria.add(Restrictions.eq("isActive", Boolean.TRUE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
