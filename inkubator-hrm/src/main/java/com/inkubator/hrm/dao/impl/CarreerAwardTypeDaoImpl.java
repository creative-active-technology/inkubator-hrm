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

import com.inkubator.hrm.dao.CarreerAwardTypeDao;
import com.inkubator.hrm.entity.CarreerAwardType;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.web.search.CarreerAwardTypeSearchParameter;
import com.inkubator.datacore.dao.impl.IDAOImpl;

@Repository(value = "carreerAwardTypeDao")
@Lazy
public class CarreerAwardTypeDaoImpl extends IDAOImpl<CarreerAwardType> implements CarreerAwardTypeDao{
	
	@Override
    public Class<CarreerAwardType> getEntityClass() {
        return CarreerAwardType.class;
    }
	
	@Override
	public List<CarreerAwardType> getByParam(CarreerAwardTypeSearchParameter searchParameter, int firstResult,
			int maxResult, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(CarreerAwardTypeSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public void doSearchByParam(CarreerAwardTypeSearchParameter searchParameter, Criteria criteria){
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
	public CarreerAwardType getEntityByPkWithDetail(long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("systemLetterReferenceByLetterTemplateId", FetchMode.JOIN);
		criteria.setFetchMode("systemLetterReferenceByCertificateLetterTemplateId", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		return (CarreerAwardType) criteria.uniqueResult();
	}
	

}
