/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EducationHistoryDao;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EducationHistory;
import com.inkubator.hrm.web.search.EducationHistorySearchParameter;
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
@Repository(value = "educationHistoryDao")
@Lazy
public class EducationHistoryDaoImpl extends IDAOImpl<EducationHistory> implements EducationHistoryDao{

    @Override
    public Class<EducationHistory> getEntityClass() {
        return EducationHistory.class;
    }

    @Override
    public List<EducationHistory> getByParam(EducationHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEducationHistoryByParam(searchParameter, criteria);
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.setFetchMode("institutionEducation", FetchMode.JOIN);
        criteria.setFetchMode("faculty", FetchMode.JOIN);
        criteria.setFetchMode("major", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalEducationHistoryByParam(EducationHistorySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEducationHistoryByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchEducationHistoryByParam(EducationHistorySearchParameter parameter, Criteria criteria) {
        if (parameter.getCertificateNumber()!= null) {
            criteria.add(Restrictions.like("certificateNumber", parameter.getCertificateNumber(), MatchMode.ANYWHERE));
        }
        
//        if (parameter.getProvinceName()!= null) {
//            criteria.createAlias("province", "p", JoinType.INNER_JOIN);
//            criteria.add(Restrictions.like("p.provinceName", parameter.getProvinceName(), MatchMode.ANYWHERE));
//        }
        
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public EducationHistory getAllDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.setFetchMode("institutionEducation", FetchMode.JOIN);
        criteria.setFetchMode("faculty", FetchMode.JOIN);
        criteria.setFetchMode("major", FetchMode.JOIN);
        return (EducationHistory) criteria.uniqueResult();
    }
    
}
