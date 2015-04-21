/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewApplicationDao")
@Lazy
public class LoanNewApplicationDaoImpl extends IDAOImpl<LoanNewApplication> implements LoanNewApplicationDao {

    @Override
    public Class<LoanNewApplication> getEntityClass() {
        return LoanNewApplication.class;
    }

    @Override
    public LoanNewApplication getEntityByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return (LoanNewApplication) criteria.uniqueResult();
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<LoanNewApplication> getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewType.id", loanNewTypeId));
        criteria.add(Restrictions.ne("loanStatus", HRMConstant.LOAN_PAID));
        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchemaId));

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_DISBURSED));
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_PAID));
        criteria.add(disjunction);

        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getByParamByStatusUndisbursed(LoanNewSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_UNDISBURSED));
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamByStatusUndisbursed(LoanNewSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_UNDISBURSED));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LoanNewSearchParameter parameter, Criteria criteria) {

        if (StringUtils.isNotEmpty(parameter.getLoanType())) {
            criteria.createAlias("loanNewType", "loanNewType", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("loanNewType.loanTypeName", parameter.getLoanType(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
            criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
            criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
