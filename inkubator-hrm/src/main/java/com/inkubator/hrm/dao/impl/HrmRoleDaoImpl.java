/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmRoleDao;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.web.search.HrmRoleSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "hrmRoleDao")
@Lazy
public class HrmRoleDaoImpl extends IDAOImpl<HrmRole> implements HrmRoleDao{

    @Override
    public Class<HrmRole> getEntityClass() {
       return HrmRole.class;
    }
    
    
    @Override
    public List<HrmRole> getByParam(HrmRoleSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLHrmRoleByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalHrmRoleByParam(HrmRoleSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLHrmRoleByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchLHrmRoleByParam(HrmRoleSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getRoleName()!= null) {
            criteria.add(Restrictions.like("roleName", searchParameter.getRoleName(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public HrmRole getByRoleName(String name) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
         criteria.add(Restrictions.eq("roleName", name));
         return (HrmRole) criteria.uniqueResult();
    }


	@Override
	public HrmRole getEntityByPkWithMenus(long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("hrmMenuRoles", FetchMode.JOIN);
		criteria.setFetchMode("hrmMenuRoles.hrmMenu", FetchMode.JOIN);
		return (HrmRole) criteria.uniqueResult();
	}


	@Override
	public HrmRole updateAndMerge(HrmRole role) {
		return (HrmRole) getCurrentSession().merge(role);
	}
}
