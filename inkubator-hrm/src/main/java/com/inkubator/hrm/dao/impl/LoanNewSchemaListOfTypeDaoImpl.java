/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewSchemaListOfTypeDao;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewSchemaListOfTypeId;
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
 * @author Deni
 */
@Repository(value = "loanNewSchemaListOfTypeDao")
@Lazy
public class LoanNewSchemaListOfTypeDaoImpl extends IDAOImpl<LoanNewSchemaListOfType> implements LoanNewSchemaListOfTypeDao{

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
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeIdWithDetail(LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", loanNewSchemaListOfTypeId));
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return (LoanNewSchemaListOfType) criteria.uniqueResult();
    }
    
    @Override
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeId(LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", loanNewSchemaListOfTypeId));
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
    public Long getTotalByNotLoanTypeAndSchema(Long typeId, Long schemaId, LoanNewSchemaListOfTypeId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanNewSchema.id", schemaId));
        criteria.add(Restrictions.eq("loanNewType.id", typeId));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
}
