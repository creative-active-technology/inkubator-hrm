/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OrgTypeOfSpecListDao;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.OrgTypeOfSpecListSearchParameter;

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

/**
 *
 * @author EKA
 */
@Repository(value = "orgTypeOfSpecListDao")
@Lazy
public class OrgTypeOfSpecListDaoImpl extends IDAOImpl<OrgTypeOfSpecList> implements OrgTypeOfSpecListDao {

    @Override
    public Class<OrgTypeOfSpecList> getEntityClass() {
        return OrgTypeOfSpecList.class;
    }

    @Override
    public List<OrgTypeOfSpecList> getByParam(OrgTypeOfSpecListSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.setFetchMode("orgTypeOfSpec", FetchMode.JOIN);        
        criteria.createAlias("orgTypeOfSpec", "orgTypeOfSpec", JoinType.INNER_JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalOrgTypeOfSpecListByParam(OrgTypeOfSpecListSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(OrgTypeOfSpecListSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public OrgTypeOfSpecList getSpecTypeNameByOrgTypeOfSpecListId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("orgTypeOfSpec", FetchMode.JOIN);
        return (OrgTypeOfSpecList) criteria.uniqueResult();
    }

    @Override
    public List<OrgTypeOfSpecList> getOrgTypeOfSpecList(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("orgTypeOfSpec", "op", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("op.id", id));
        return criteria.list();
    }

    @Override
    public List<OrgTypeOfSpecList> getAllDataByOrgTypeOfSpecIdAndOrderByCode(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("orgTypeOfSpec.id", id));
        criteria.setFetchMode("orgTypeOfSpec", FetchMode.JOIN);
        criteria.addOrder(Order.asc("code"));
        return criteria.list();
    }

	@Override
	public OrgTypeOfSpecList getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("orgTypeOfSpec", FetchMode.JOIN);
        criteria.setFetchMode("orgTypeOfSpecListKlasifikasis", FetchMode.JOIN);
        criteria.setFetchMode("orgTypeOfSpecListKlasifikasis.klasifikasiKerja", FetchMode.JOIN);
        return (OrgTypeOfSpecList) criteria.uniqueResult();
	}

	@Override
	public void saveAndMerge(OrgTypeOfSpecList orgTypeOfSpecList) {
		getCurrentSession().update(orgTypeOfSpecList);
        getCurrentSession().flush();
	}

}
