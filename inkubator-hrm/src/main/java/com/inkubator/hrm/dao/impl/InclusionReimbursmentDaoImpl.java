/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.InclusionReimbursmentDao;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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
 * @author Deni
 */
@Repository(value = "inclusionReimbursmentDao")
@Lazy
public class InclusionReimbursmentDaoImpl extends IDAOImpl<Reimbursment> implements InclusionReimbursmentDao{

    @Override
    public Class<Reimbursment> getEntityClass() {
        return Reimbursment.class;
    }

    @Override
    public List<Reimbursment> getByParam(String parameter, InclusionReimbursmentModel inclusionReimbursmentModel, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria, inclusionReimbursmentModel);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalResourceTypeByParam(String parameter, InclusionReimbursmentModel inclusionReimbursmentModel) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria, inclusionReimbursmentModel);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(String parameter, Criteria criteria, InclusionReimbursmentModel inclusionReimbursmentModel) {
        criteria.createAlias("reimbursmentSchema", "reimbursmentSchema", JoinType.INNER_JOIN);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("reimbursmentSchema.payrollComponent", Boolean.TRUE));
        criteria.add(Restrictions.ge("claimDate", inclusionReimbursmentModel.getStartDate())); 
        criteria.add(Restrictions.lt("claimDate", inclusionReimbursmentModel.getEndDate()));
        if (StringUtils.isNotEmpty(parameter)) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter, MatchMode.ANYWHERE));
            criteria.add(disjunction);
//            criteria.add(Restrictions.like("bioData.firstName", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<Reimbursment> getByWtPeriodeWhereComponentPayrollIsActive(InclusionReimbursmentModel inclusionReimbursmentModel) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("reimbursmentSchema", "reimbursmentSchema", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("reimbursmentSchema.payrollComponent", Boolean.TRUE));
        criteria.add(Restrictions.ge("claimDate", inclusionReimbursmentModel.getStartDate())); 
        criteria.add(Restrictions.lt("claimDate", inclusionReimbursmentModel.getEndDate()));
        return criteria.list();
    }
}
