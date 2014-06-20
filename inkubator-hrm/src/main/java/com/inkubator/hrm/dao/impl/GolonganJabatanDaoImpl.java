/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.web.search.GolonganJabatanSearchParameter;

/**
 *
 * @author Deni Husni FR,rizkykojek
 */
@Repository(value = "golonganJabatanDao")
public class GolonganJabatanDaoImpl extends IDAOImpl<GolonganJabatan> implements GolonganJabatanDao {

    @Override
    public Class<GolonganJabatan> getEntityClass() {
        return GolonganJabatan.class;
    }

	@Override
	public List<GolonganJabatan> getByParam(GolonganJabatanSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(GolonganJabatanSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(GolonganJabatanSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("pangkat", "pangkat", JoinType.LEFT_OUTER_JOIN);
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getPangkatName())) {
            criteria.add(Restrictions.like("pangkat.pangkatName", parameter.getPangkatName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public Long getTotalByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByNameAndNotId(String name, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByCode(String code) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByCodeAndNotId(String code, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
    public GolonganJabatan getEntityByPkFetchPangkat(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("pangkat", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (GolonganJabatan) criteria.uniqueResult();
    }

}
