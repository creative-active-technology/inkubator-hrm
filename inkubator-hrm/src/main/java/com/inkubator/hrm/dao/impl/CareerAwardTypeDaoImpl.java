package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.hrm.dao.CareerAwardTypeDao;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.web.search.CareerAwardTypeSearchParameter;
import com.inkubator.datacore.dao.impl.IDAOImpl;

@Repository(value = "careerAwardTypeDao")
@Lazy
public class CareerAwardTypeDaoImpl extends IDAOImpl<CareerAwardType> implements CareerAwardTypeDao{
	
	@Override
    public Class<CareerAwardType> getEntityClass() {
        return CareerAwardType.class;
    }
	
	@Override
	public List<CareerAwardType> getByParam(CareerAwardTypeSearchParameter searchParameter, int firstResult,
			int maxResult, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(CareerAwardTypeSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public void doSearchByParam(CareerAwardTypeSearchParameter searchParameter, Criteria criteria){
		criteria.createAlias("systemLetterReferenceByLetterTemplateId", "systemLetterReferenceByLetterTemplateId", JoinType.INNER_JOIN);
		criteria.createAlias("systemLetterReferenceByCertificateLetterTemplateId", "systemLetterReferenceByCertificateLetterTemplateId",  JoinType.INNER_JOIN);
		
		if(searchParameter.getCode() != null){
			criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
		}
		if(searchParameter.getName() != null){
			criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
		}
		criteria.add(Restrictions.isNotNull("id"));
	}

	@Override
	public CareerAwardType getEntityByPkWithDetail(long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("systemLetterReferenceByLetterTemplateId", FetchMode.JOIN);
		criteria.setFetchMode("systemLetterReferenceByCertificateLetterTemplateId", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		return (CareerAwardType) criteria.uniqueResult();
	}
	

}
