package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AdmonitionTypeDao;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;
import java.util.List;
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
@Repository(value = "admonitionTypeDao")
@Lazy
public class AdmonitionTypeDaoImpl extends IDAOImpl<AdmonitionType> implements AdmonitionTypeDao {

    @Override
    public Class<AdmonitionType> getEntityClass() {
        return AdmonitionType.class;
    }

    @Override
    public List<AdmonitionType> getByParam(AdmonitionTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAdmonitionTypeByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalAdmonitionTypeByParam(AdmonitionTypeSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAdmonitionTypeByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchAdmonitionTypeByParam(AdmonitionTypeSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode()!= null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getName()!= null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

}
