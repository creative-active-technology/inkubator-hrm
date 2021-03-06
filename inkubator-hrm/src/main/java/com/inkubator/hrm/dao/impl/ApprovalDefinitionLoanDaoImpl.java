/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionLoanDao;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.ApprovalDefinitionLoanId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository
@Lazy
public class ApprovalDefinitionLoanDaoImpl extends IDAOImpl<ApprovalDefinitionLoan> implements ApprovalDefinitionLoanDao {

    @Override
    public Class<ApprovalDefinitionLoan> getEntityClass() {
        return ApprovalDefinitionLoan.class;

    }

    @Override
    public List<ApprovalDefinitionLoan> getByLoanId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanNewSchema.id", id));
        return criteria.list();
    }

    @Override
    public ApprovalDefinitionLoan getEntityByPk(Long appDefId, Long loanNewSchema) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchema));
        criteria.add(Restrictions.eq("approvalDefinition.id", appDefId));
        return (ApprovalDefinitionLoan) criteria.uniqueResult();
    }

    @Override
    public List<ApprovalDefinitionLoan> getByLoanIdWithDetail(Long id) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition.hrmUserByApproverIndividual", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition.hrmUserByOnBehalfIndividual", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition.jabatanByApproverPosition", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinition.jabatanByOnBehalfPosition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanNewSchema.id", id));
        return criteria.list();
    }

}
