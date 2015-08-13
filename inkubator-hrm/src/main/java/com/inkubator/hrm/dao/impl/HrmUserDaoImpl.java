/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "hrmUserDao")
@Lazy
public class HrmUserDaoImpl extends IDAOImpl<HrmUser> implements HrmUserDao {

    @Override
    public Class<HrmUser> getEntityClass() {
        return HrmUser.class;
    }

    @Override
    public HrmUser getByUserId(String userId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("userId", userId));
        return (HrmUser) criteria.uniqueResult();
    }

    private void doSearchSpiUserByParam(HrmUserSearchParameter parameter, Criteria criteria) {
        if (parameter.getUserName() != null) {
            criteria.add(Restrictions.like("userId", parameter.getUserName(), MatchMode.ANYWHERE));

        }

        if (parameter.getRealName() != null) {
            criteria.add(Restrictions.like("realName", parameter.getRealName(), MatchMode.ANYWHERE));
        }

        if (parameter.getRoleName() != null) {
            criteria.createAlias("hrmUserRoles", "h");
            criteria.createAlias("h.hrmRole", "hrmRole");
            criteria.add(Restrictions.like("hrmRole.roleName", parameter.getRoleName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<HrmUser> getByParam(HrmUserSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchSpiUserByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalHrmUserByParam(HrmUserSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchSpiUserByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public HrmUser getByEmailAddress(String emailAddress) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("emailAddress", emailAddress));
        return (HrmUser) criteria.uniqueResult();
    }

    @Override
    public void saveAndMerge(HrmUser hrmUser) {
        getCurrentSession().update(hrmUser);
        getCurrentSession().flush();
    }

    @Override
    public HrmUser getByUserIdOrEmail(String param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("userId", param));
        disjunction.add(Restrictions.eq("emailAddress", param));
        criteria.add(disjunction);
        
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (HrmUser) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByEmailAndNotUserId(String emailAddress, String userId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("emailAddress", emailAddress));
        criteria.add(Restrictions.ne("userId", userId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public HrmUser getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (HrmUser) criteria.uniqueResult();
    }

    @Override
    public List<HrmUser> getByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("realName", name,MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("isActive", HRMConstant.ACTIVE));
        criteria.setFirstResult(0);
        criteria.setMaxResults(7);
        return criteria.list();
    }

	@Override
	public HrmUser getByEmpDataId(long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		return (HrmUser) criteria.uniqueResult();
	}

	@Override
	public List<HrmUser> getAllDataByNameOrNik(String param) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
		Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("bioData.firstName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("bioData.lastName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("empData.nik", param, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        return criteria.list();
	}

    @Override
    public HrmUser getByEmailAddressInNotLock(String emailAddress) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("emailAddress", emailAddress));
        criteria.add(Restrictions.eq("isLock", 0));
        return (HrmUser) criteria.uniqueResult();
        
    }

	@Override
	public HrmUser getByUserIdWithDetail(String userId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("userId", userId));
        return (HrmUser) criteria.uniqueResult();
	}

}
