package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitMppApplyDao;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
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
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitMppApplyDao")
@Lazy
public class RecruitMppApplyDaoImpl extends IDAOImpl<RecruitMppApply> implements RecruitMppApplyDao {

    @Override
    public Class<RecruitMppApply> getEntityClass() {
        return RecruitMppApply.class;
    }

    @Override
    public List<RecruitMppApply> getByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        doSearchLoanTypeByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);                
        return criteria.list();
        
       
    }

    @Override
    public Long getTotalByParam(RecruitMppApplySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLoanTypeByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchLoanTypeByParam(RecruitMppApplySearchParameter parameter, Criteria criteria) {
        if (parameter.getRecruitMppApplyCode() != null) {
            criteria.add(Restrictions.like("recruitMppApplyCode", parameter.getRecruitMppApplyCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getRecruitMppApplyName() != null) {
            criteria.add(Restrictions.like("recruitMppApplyName", parameter.getRecruitMppApplyName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public RecruitMppApply getEntityWithDetailById(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);       
        return (RecruitMppApply) criteria.uniqueResult();
    }
}
