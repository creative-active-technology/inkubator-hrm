package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmMenuDao;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "hrmMenuDao")
public class HrmMenuDaoImpl extends IDAOImpl<HrmMenu> implements HrmMenuDao {

	@Override
	public Class<HrmMenu> getEntityClass() {
		return HrmMenu.class;
		
	}
	
	@Override
	public List<HrmMenu> getByParam(HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria = doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(HrmMenuSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria = doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private Criteria doSearchByParam(HrmMenuSearchParameter parameter, Criteria criteria) {        
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
        
        return criteria;
    }

	@Override
	public List<HrmMenu> getAllDataByLevel(Integer level) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("menuLevel", level));
		return criteria.list();
		
	}

	@Override
	public HrmMenu getEntityByPkWithDetail(long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("hrmMenu", FetchMode.JOIN);
		return (HrmMenu) criteria.uniqueResult();
	}

	@Override
	public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("menuLevel", level));
		criteria.add(Restrictions.ne("id", id));
		return criteria.list();
	}

	@Override
	public List<HrmMenu> getAllDataByParamAndNotRoleId(Long roleId, HrmMenuSearchParameter parameter, int firstResult, int maxResults,
			Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria = doSearchByParamAndNotRoleId(roleId, parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParamAndNotRoleId(Long roleId, HrmMenuSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria = doSearchByParamAndNotRoleId(roleId, parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private Criteria doSearchByParamAndNotRoleId(Long roleId, HrmMenuSearchParameter parameter, Criteria criteria) {
		Criterion orCondition  = Restrictions.disjunction().add(Restrictions.ne("h.hrmRole.id", roleId)).add(Restrictions.isNull("h.hrmRole.id"));
		criteria.createAlias("hrmMenuRoles", "h", JoinType.LEFT_OUTER_JOIN);
		criteria.add(orCondition);
		return doSearchByParam(parameter, criteria);
	}

}
