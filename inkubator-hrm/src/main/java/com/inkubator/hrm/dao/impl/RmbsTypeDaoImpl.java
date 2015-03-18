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
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.web.search.RmbsTypeSearchParameter;

@Repository(value = "rmbsTypeDao")
@Lazy
public class RmbsTypeDaoImpl extends IDAOImpl<RmbsType> implements RmbsTypeDao {

	@Override
	public Class<RmbsType> getEntityClass() {
		// TODO Auto-generated method stub
		return RmbsType.class;
	}

	@Override
	public List<RmbsType> getByParam(RmbsTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
		
	}

	@Override
	public Long getTotalByParam(RmbsTypeSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		
	}
	
	private void doSearchByParam(RmbsTypeSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("costCenter", "costCenter");
		
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCoa())) {        	
            criteria.add(Restrictions.like("costCenter.name", parameter.getCoa(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public List<RmbsType> getAllDataByStatusActive() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("isActive", Boolean.TRUE));
		return criteria.list();
		
	}

	@Override
	public RmbsType getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("costCenter", FetchMode.JOIN);
		return (RmbsType) criteria.uniqueResult();
	}

	
}
