package com.inkubator.hrm.dao.impl;

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

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregPayComponentsDao;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.web.search.UnregPayComponentsSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "unregPayComponentsDao")
@Lazy
public class UnregPayComponentsDaoImpl extends IDAOImpl<UnregPayComponents> implements UnregPayComponentsDao {

	@Override
	public Class<UnregPayComponents> getEntityClass() {
		return UnregPayComponents.class;
	}

	@Override
	public List<UnregPayComponents> getByParam(UnregPayComponentsSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());		
        this.doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(UnregPayComponentsSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        this.doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchByParam(UnregPayComponentsSearchParameter searchParameter, Criteria criteria) {
		criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
		criteria.createAlias("paySalaryComponent.modelComponent", "modelComponent", JoinType.INNER_JOIN);
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("paySalaryComponent.name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getModelComponentName() != null) {
        	criteria.add(Restrictions.like("modelComponent.name", searchParameter.getModelComponentName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("paySalaryComponent.code", searchParameter.getCode(), MatchMode.START));
        }
        if (searchParameter.getUnregSalaryId() != null){
        	criteria.add(Restrictions.eq("unregSalary.id", searchParameter.getUnregSalaryId()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public UnregPayComponents getEntityByPkWithDetail(Long unregPayComponentsId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", unregPayComponentsId));
		criteria.setFetchMode("unregSalary", FetchMode.JOIN);
		criteria.setFetchMode("unregSalary.wtPeriode", FetchMode.JOIN);
		criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
		return (UnregPayComponents) criteria.uniqueResult();
	}
	
}
