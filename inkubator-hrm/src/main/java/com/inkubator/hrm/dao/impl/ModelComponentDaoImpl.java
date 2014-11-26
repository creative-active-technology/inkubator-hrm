package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ModelComponentDao;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.web.search.ModelComponentSearchParameter;
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
 * @author Taufik Hidayat
 */
@Repository(value = "modelComponentDao")
@Lazy
public class ModelComponentDaoImpl extends IDAOImpl<ModelComponent> implements ModelComponentDao {

    @Override
    public Class<ModelComponent> getEntityClass() {
        return ModelComponent.class;
    }

    @Override
    public List<ModelComponent> getByParam(ModelComponentSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchModelComponentByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalModelComponentByParam(ModelComponentSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchModelComponentByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchModelComponentByParam(ModelComponentSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public ModelComponent getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("benefitGroup", FetchMode.JOIN);
        return (ModelComponent) criteria.uniqueResult();
    }

  
}
