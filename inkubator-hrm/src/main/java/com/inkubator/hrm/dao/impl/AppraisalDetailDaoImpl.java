package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalDetailDao;
import com.inkubator.hrm.entity.AppraisalDetail;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
 * @author Taufik Hidayat
 */
@Repository(value = "appraisalDetailDao")
@Lazy
public class AppraisalDetailDaoImpl extends IDAOImpl<AppraisalDetail> implements AppraisalDetailDao {

    @Override
    public Class<AppraisalDetail> getEntityClass() {
        return AppraisalDetail.class;
    }

    @Override
    public List<AppraisalDetail> getByParam(String parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAppraisalDetailByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalAppraisalDetailByParam(String parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAppraisalDetailByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchAppraisalDetailByParam(String parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter)) {
            criteria.add(Restrictions.like("name", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public AppraisalDetail getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("appraisalElement", FetchMode.JOIN);
        return (AppraisalDetail) criteria.uniqueResult();
    }

}
