package com.inkubator.hrm.dao.impl;

import com.inkubator.hrm.entity.PaCompetencyPoint;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.PaCompetencyPointDao;
import com.inkubator.hrm.web.search.PaCompetencyPointSearchParameter;

/**
 *
 * @author WebGenX
 */
@Repository(value = "paCompetencyPointDao")
@Lazy
public class PaCompetencyPointDaoImpl extends IDAOImpl<PaCompetencyPoint> implements PaCompetencyPointDao {

    @Override
    public Class<PaCompetencyPoint> getEntityClass() {
        return PaCompetencyPoint.class;
    }

    @Override
    public List<PaCompetencyPoint> getByParam(PaCompetencyPointSearchParameter parameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPaCompetencyPointByParam(parameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPaCompetencyPointByParam(PaCompetencyPointSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPaCompetencyPointByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchPaCompetencyPointByParam(PaCompetencyPointSearchParameter parameter, Criteria criteria) {
        if (parameter.getDescription() != null) {
            criteria.add(Restrictions.like("description", parameter.getDescription(), MatchMode.ANYWHERE));
        }
        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
