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
import com.inkubator.hrm.dao.CareerDisciplineTypeDao;
import com.inkubator.hrm.entity.CareerDisciplineType;
import com.inkubator.hrm.web.search.CareerDisciplineTypeSearchParameter;

@Repository(value = "careerDisciplineTypeDao")
@Lazy
public class CareerDisciplineTypeDaoImpl extends IDAOImpl<CareerDisciplineType> implements CareerDisciplineTypeDao{

	@Override
	public Class<CareerDisciplineType> getEntityClass(){
		return CareerDisciplineType.class;
	}
	
	@Override
	public List<CareerDisciplineType> getByParam(CareerDisciplineTypeSearchParameter searchParameter, int firstResult,
			int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalDataByParam(CareerDisciplineTypeSearchParameter searchParameter) {
		// TODO Auto-generated method stub
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public void doSearchByParam(CareerDisciplineTypeSearchParameter searchParameter, Criteria criteria){
		criteria.createAlias("systemLetterReference", "systemLetterReference", JoinType.INNER_JOIN);
		if(searchParameter.getCode() != null){
			criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
		}
		if(searchParameter.getName() != null){
			criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
		}
		criteria.add(Restrictions.isNotNull("id"));
	}
	
	@Override
	public CareerDisciplineType getEntityWithDetail(long id){
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("systemLetterReference", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", id));
		return (CareerDisciplineType) criteria.uniqueResult();
	}
}
