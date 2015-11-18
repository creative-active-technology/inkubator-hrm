package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CareerEmpStatusDao;
import com.inkubator.hrm.entity.CareerEmpStatus;
import com.inkubator.hrm.web.search.CareerEmpStatusSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "careerEmpStatusDao")
@Lazy
public class CareerEmpStatusDaoImpl extends IDAOImpl<CareerEmpStatus> implements CareerEmpStatusDao {

	@Override
	public Class<CareerEmpStatus> getEntityClass() {
		// TODO Auto-generated method stub
		return CareerEmpStatus.class;
	}

	@Override
	public List<CareerEmpStatus> getByParam(CareerEmpStatusSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(CareerEmpStatusSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchByParam(CareerEmpStatusSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("empType", "empType");
		
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmpTypeName())) {        	
            criteria.add(Restrictions.like("empType.name", parameter.getEmpTypeName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
		
	@Override
	public CareerEmpStatus getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("empType", FetchMode.JOIN);
		criteria.setFetchMode("systemLetterReference", FetchMode.JOIN);
		return (CareerEmpStatus) criteria.uniqueResult();
	}

}
