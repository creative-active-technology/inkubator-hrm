package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DocumentDao;
import com.inkubator.hrm.entity.Document;
import com.inkubator.hrm.web.search.DocumentSearchParameter;

@Repository(value = "documentDao")
@Lazy
public class DocumentDaoImpl extends IDAOImpl<Document> implements DocumentDao{

	@Override
	public List<Document> getByParam(DocumentSearchParameter searchParameter, int firstResult, int maxResults,
			Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(DocumentSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Class<Document> getEntityClass() {
		return Document.class;
	}
	
	public void doSearchByParam(DocumentSearchParameter searchParameter, Criteria criteria){
		if(searchParameter.getName() != null){
			criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
		}
		if(searchParameter.getCode() != null){
			criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.isNotNull("id"));
	}

}
