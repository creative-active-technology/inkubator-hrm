/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ReimbursmentSchemaDao;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.ReimbursmentSchemaSearchParameter;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "reimbursmentSchemaDao")
@Lazy
public class ReimbursmentSchemaDaoImpl extends IDAOImpl<ReimbursmentSchema> implements ReimbursmentSchemaDao {

    @Override
    public Class<ReimbursmentSchema> getEntityClass() {
        return ReimbursmentSchema.class;
    }

    @Override
    public List<ReimbursmentSchema> getAllDataWithAllRelation(ReimbursmentSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReimbursmentByParam(searchParameter, criteria);
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReimbursmentByParam(ReimbursmentSchemaSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReimbursmentByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public ReimbursmentSchema getEntityByPkWithAllRelation(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("employeeTypes", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchemaEmployeeTypes", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchemaEmployeeTypes.employeeType", FetchMode.JOIN);
        return (ReimbursmentSchema) criteria.uniqueResult();
    }

    @Override
    public Long getByReimbursmentCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchReimbursmentByParam(ReimbursmentSchemaSearchParameter searchParameter, Criteria criteria) {
        System.out.println(searchParameter.getNominalUnit() + " dao");
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getNominalUnit() != null) {
            criteria.add(Restrictions.eq("nominalUnit", searchParameter.getNominalUnit()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public void saveAndMerge(ReimbursmentSchema reimbursmentSchema) {
        getCurrentSession().update(reimbursmentSchema);
        getCurrentSession().flush();
    }

    @Override
    public List<ReimbursmentSchema> isPayrollComponent(Long id) {
        //ngambil data yang ada di dalem pay salary component
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.groupProperty("modelReffernsil"));
        DetachedCriteria subQuery = DetachedCriteria.forClass(PaySalaryComponent.class);
        subQuery.createAlias("modelComponent", "mc").add(Restrictions.eq("mc.id", id));
        subQuery.setProjection(proList);
//        List<Integer> list = subQuery.getExecutableCriteria(getCurrentSession()).list();
        //ngambil data yang payroll componentnya 1, dan idnya tidak sama dengan id subquery
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("payrollComponent", Boolean.TRUE));
        criteria.add(Property.forName("id").notIn(subQuery));
        return criteria.list();
    }

    @Override
    public String getReimbursmentSchemaNameByPk(Long id) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (String) criteria.setProjection(Projections.property("name")).uniqueResult();
    }
}
