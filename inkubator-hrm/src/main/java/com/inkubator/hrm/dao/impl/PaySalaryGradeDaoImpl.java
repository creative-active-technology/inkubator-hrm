/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PaySalaryGradeDao;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.web.search.PaySalaryGradeSearchParameter;
import java.math.BigDecimal;
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
 * @author Deni
 */
@Repository(value = "paySalaryGradeDao")
@Lazy
public class PaySalaryGradeDaoImpl extends IDAOImpl<PaySalaryGrade> implements PaySalaryGradeDao{

    @Override
    public Class<PaySalaryGrade> getEntityClass() {
        return PaySalaryGrade.class;
    }

    @Override
    public List<PaySalaryGrade> getByParam(PaySalaryGradeSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPaySalaryGradeByParam(searchParameter, criteria);
        criteria.setFetchMode("currency", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPaySalaryGradeByParam(PaySalaryGradeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPaySalaryGradeByParam(searchParameter, criteria);
        criteria.setFetchMode("currency", FetchMode.JOIN);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchPaySalaryGradeByParam(PaySalaryGradeSearchParameter searchParameter, Criteria criteria) {
        BigDecimal bg = new BigDecimal("0");
        int resMinSalary = 0;
        int resMedSalary = 0;
        int resMaxSalary = 0;
        if(searchParameter.getMinSalary()!= null && !String.valueOf(searchParameter.getMinSalary()).equals("")){
            resMinSalary = searchParameter.getMinSalary().compareTo(bg);
        }
        if(searchParameter.getMedSalary()!= null && !String.valueOf(searchParameter.getMedSalary()).equals("")){
            resMedSalary = searchParameter.getMedSalary().compareTo(bg);
        }
        if(searchParameter.getMaxSalary()!= null&&!String.valueOf(searchParameter.getMaxSalary()).equals("")){
            resMaxSalary = searchParameter.getMaxSalary().compareTo(bg);
        }
        
        if (searchParameter.getGradeSalary()!= null && searchParameter.getGradeSalary()!= 0) {
            criteria.add(Restrictions.eq("gradeSalary", searchParameter.getGradeSalary()));
        }
        if (searchParameter.getMinSalary()!= null && resMinSalary != 0) {
            criteria.add(Restrictions.eq("minSalary", searchParameter.getMinSalary()));
        }
        if (searchParameter.getMedSalary()!= null && resMedSalary != 0) {
            criteria.add(Restrictions.eq("mediumSalary", searchParameter.getMedSalary()));
        }
        if (searchParameter.getMaxSalary()!= null && resMaxSalary != 0) {
            criteria.add(Restrictions.eq("maxSalary", searchParameter.getMaxSalary()));
        }
        if (searchParameter.getCurrency()!= null) {
            criteria.createAlias("currency", "c", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("c.name", searchParameter.getCurrency(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PaySalaryGrade getByPaySalaryGradeId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.ne("id", id));
        criteria.setFetchMode("currency", FetchMode.JOIN);
        return (PaySalaryGrade) criteria.uniqueResult();
    }

}
