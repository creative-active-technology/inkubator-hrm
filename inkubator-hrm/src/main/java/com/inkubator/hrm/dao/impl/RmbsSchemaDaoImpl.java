package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsSchemaDao;
import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.web.search.RmbsSchemaSearchParameter;

@Repository(value = "rmbsSchemaDao")
@Lazy
public class RmbsSchemaDaoImpl extends IDAOImpl<RmbsSchema> implements RmbsSchemaDao {
	
	@Override
	public Class<RmbsSchema> getEntityClass() {
		return RmbsSchema.class;
	}

	@Override
	public List<RmbsSchema> getByParam(RmbsSchemaSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
		
	}

	@Override
	public Long getTotalByParam(RmbsSchemaSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(RmbsSchemaSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public List<RmbsSchema> getAllDataByStatusActive() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("isActive", Boolean.TRUE));
		return criteria.list();
		
	}

	@Override
	public Long getTotalByNomorSk(String nomorSk) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("nomorSk", nomorSk));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		
	}

	@Override
	public Long getTotalByNomorSkAndNotId(String nomorSk, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("nomorSk", nomorSk));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		
	}

}
