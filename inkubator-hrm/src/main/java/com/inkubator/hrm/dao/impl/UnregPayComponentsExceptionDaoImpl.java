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
import com.inkubator.hrm.dao.UnregPayComponentsExceptionDao;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregPayComponentsExceptionId;
import com.inkubator.hrm.web.search.UnregPayComponentExceptionSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "unregPayComponentsExceptionDao")
@Lazy
public class UnregPayComponentsExceptionDaoImpl extends IDAOImpl<UnregPayComponentsException> implements UnregPayComponentsExceptionDao {

	@Override
	public Class<UnregPayComponentsException> getEntityClass() {
		return UnregPayComponentsException.class;
		
	}

	@Override
	public List<UnregPayComponentsException> getByParam(UnregPayComponentExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(UnregPayComponentExceptionSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(UnregPayComponentExceptionSearchParameter searchParameter, Criteria criteria) {
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("bioData.firstName", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getNik() != null) {
            criteria.add(Restrictions.like("empData.nik", searchParameter.getNik(), MatchMode.START));
        }
        if(searchParameter.getUnregPayComponentsId() != null){
        	criteria.add(Restrictions.eq("unregPayComponents.id", searchParameter.getUnregPayComponentsId()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public UnregPayComponentsException getEntityByPK(UnregPayComponentsExceptionId id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id.unregPayComponentsId", id.getUnregPayComponentsId()));
		criteria.add(Restrictions.eq("id.empDataId", id.getEmpDataId()));
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		return (UnregPayComponentsException) criteria.uniqueResult();
	}

	@Override
	public List<UnregPayComponentsException> getAllDataByUnregSalaryId(Long unregSalaryId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("unregPayComponents", "unregPayComponents");
		criteria.createAlias("unregPayComponents.unregSalary", "unregSalary");
		criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
		return criteria.list();
		
	}

	
}
