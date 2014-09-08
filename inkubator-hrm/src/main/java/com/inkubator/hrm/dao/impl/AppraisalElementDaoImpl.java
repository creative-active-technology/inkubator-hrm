package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalElementDao;
import com.inkubator.hrm.entity.AppraisalElement;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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
@Repository(value = "appraisalElementDao")
@Lazy
public class AppraisalElementDaoImpl extends IDAOImpl<AppraisalElement> implements AppraisalElementDao {

    @Override
    public Class<AppraisalElement> getEntityClass() {
        return AppraisalElement.class;
    }

    @Override
    public List<AppraisalElement> getByParam(String parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAppraisalElementByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalAppraisalElementByParam(String parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAppraisalElementByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchAppraisalElementByParam(String parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter)) {
            criteria.add(Restrictions.like("name", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }


}
