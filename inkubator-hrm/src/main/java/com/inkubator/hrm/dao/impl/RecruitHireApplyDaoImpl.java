package com.inkubator.hrm.dao.impl;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;

/**
 *
 * @author WebGenX
 */
@Repository(value = "recruitHireApplyDao")
@Lazy
public class RecruitHireApplyDaoImpl extends IDAOImpl<RecruitHireApply> implements RecruitHireApplyDao {

    @Override
    public Class<RecruitHireApply> getEntityClass() {
        return RecruitHireApply.class;
    }

    @Override
    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter parameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitHireApplyByParam(parameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitHireApplyByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchRecruitHireApplyByParam(RecruitHireApplySearchParameter parameter, Criteria criteria) {
        if (parameter.getReason() != null) {
            criteria.add(Restrictions.like("reason", parameter.getReason(), MatchMode.ANYWHERE));
        }
        if (parameter.getReqHireCode() != null) {
            criteria.add(Restrictions.like("reqHireCode", parameter.getReqHireCode(), MatchMode.ANYWHERE));
        }
        if (parameter.getMaritalStatus() != null) {
            criteria.add(Restrictions.like("maritalStatus", parameter.getMaritalStatus(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
