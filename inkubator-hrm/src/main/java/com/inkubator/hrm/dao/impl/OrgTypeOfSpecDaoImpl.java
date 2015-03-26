package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OrgTypeOfSpecDao;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.web.search.OrgTypeOfSpecSearchParameter;
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
 * @author WebGenX
 */
@Repository(value = "orgTypeOfSpecDao")
@Lazy
public class OrgTypeOfSpecDaoImpl extends IDAOImpl<OrgTypeOfSpec> implements OrgTypeOfSpecDao {

    @Override
    public Class<OrgTypeOfSpec> getEntityClass() {
        return OrgTypeOfSpec.class;
    }

    @Override
    public List<OrgTypeOfSpec> getByParam(OrgTypeOfSpecSearchParameter parameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchOrgTypeOfSpecByParam(parameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalOrgTypeOfSpecByParam(OrgTypeOfSpecSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchOrgTypeOfSpecByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchOrgTypeOfSpecByParam(OrgTypeOfSpecSearchParameter parameter, Criteria criteria) {
        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
