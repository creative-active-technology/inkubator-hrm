/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanSchemaDao;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.web.search.LoanSchemaSearchParameter;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
@Repository(value = "loanSchemaDao")
@Lazy
public class LoanSchemaDaoImpl extends IDAOImpl<LoanSchema> implements LoanSchemaDao {

    @Override
    public Class<LoanSchema> getEntityClass() {
        return LoanSchema.class;
    }

    @Override
    public List<LoanSchema> getAllDataWithAllRelation(LoanSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
//        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(LoanSchemaSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public LoanSchema getEntityByPkWithAllRelation(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("employeeTypes", FetchMode.JOIN);
        criteria.setFetchMode("loanSchemaEmployeeTypes", FetchMode.JOIN);
        criteria.setFetchMode("loanSchemaEmployeeTypes.employeeType", FetchMode.JOIN);
        return (LoanSchema) criteria.uniqueResult();
    }

    @Override
    public Long getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void saveAndMerge(LoanSchema loanSchema) {
        getCurrentSession().update(loanSchema);
        getCurrentSession().flush();
    }

    private void doSearchByParam(LoanSchemaSearchParameter searchParameter, Criteria criteria) {
        BigDecimal bg = new BigDecimal("0");
        Integer resMaxNominal = 0;
        if (searchParameter.getMaxNominal() != null) {
            resMaxNominal = searchParameter.getMaxNominal().compareTo(bg);
        }
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        if (searchParameter.getMaxNominal() != null && resMaxNominal != 0) {
            criteria.add(Restrictions.eq("maxNominal", searchParameter.getMaxNominal()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LoanSchema> getAllDataByEmployeeTypeId(Long employeeTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loanSchemaEmployeeTypes", "loanSchemaEmployeeTypes");
        criteria.createAlias("loanSchemaEmployeeTypes.employeeType", "employeeType");
        criteria.add(Restrictions.eq("employeeType.id", employeeTypeId));
        return criteria.list();
    }

    @Override
    public List<LoanSchema> getEntityIsPayRollComponent() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("payrollComponent", HRMConstant.PAYROLL_COMPONENT_YES));
        return criteria.list();
    }

    @Override
    public String getLoanSchemaNameByPk(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (String) criteria.setProjection(Projections.property("name")).uniqueResult();
    }
}
