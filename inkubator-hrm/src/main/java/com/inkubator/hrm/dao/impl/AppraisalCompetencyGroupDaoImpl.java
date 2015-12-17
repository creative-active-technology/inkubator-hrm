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
import com.inkubator.hrm.dao.AppraisalCompetencyGroupDao;
import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.search.CompetencyGroupSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalCompetencyGroupDao")
@Lazy
public class AppraisalCompetencyGroupDaoImpl extends IDAOImpl<AppraisalCompetencyGroup> implements AppraisalCompetencyGroupDao {

	@Override
	public Class<AppraisalCompetencyGroup> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalCompetencyGroup.class;
	}

	@Override
	public List<AppraisalCompetencyGroup> getAllDataByParam(CompetencyGroupSearchParameter searchParameter, int firstResult, int maxResult, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(CompetencyGroupSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(CompetencyGroupSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("competencyType", "competencyType", JoinType.INNER_JOIN);
		
		if (StringUtils.isEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isEmpty(parameter.getCode())){
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isEmpty(parameter.getCompetencyTypeName())){
            criteria.add(Restrictions.like("competencyType.name", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public AppraisalCompetencyGroup getEntityByIdWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("competencyType", FetchMode.JOIN);
		return (AppraisalCompetencyGroup) criteria.uniqueResult();
	}

}