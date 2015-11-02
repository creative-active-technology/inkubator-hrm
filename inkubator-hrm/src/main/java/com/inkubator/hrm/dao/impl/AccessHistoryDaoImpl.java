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
import com.inkubator.hrm.dao.AccessHistoryDao;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.web.search.AccessHistorySearchParameter;

@Repository(value = "accessHistoryDao")
@Lazy
public class AccessHistoryDaoImpl extends IDAOImpl<RiwayatAkses> implements AccessHistoryDao{
	
	@Override
	public Class<RiwayatAkses> getEntityClass() {
		return RiwayatAkses.class;
	}

	@Override
	public List<RiwayatAkses> getByParam(AccessHistorySearchParameter searchParameter, int firstResult, int maxResults,
			Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearch(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		criteria.setFetchMode("hrmMenu", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(AccessHistorySearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearch(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearch(AccessHistorySearchParameter searchParameter, Criteria criteria){
		if(searchParameter.getUserId()!= null){
            criteria.add(Restrictions.like("userId", searchParameter.getUserId(), MatchMode.START));
        }
		criteria.add(Restrictions.isNotNull("id"));
	}
}
