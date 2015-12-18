package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyUnitDao;
import com.inkubator.hrm.entity.AppraisalCompetencyUnit;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.search.CompetencyUnitSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalCompetencyUnitDao")
@Lazy
public class AppraisalCompetencyUnitDaoImpl extends IDAOImpl<AppraisalCompetencyUnit> implements AppraisalCompetencyUnitDao {

	@Override
	public Class<AppraisalCompetencyUnit> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalCompetencyUnit.class;
	}

	@Override
	public List<AppraisalCompetencyUnit> getAllDataByParam(CompetencyUnitSearchParameter searchParameter, int firstResult, int maxResult, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(CompetencyUnitSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(CompetencyUnitSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("competencyGroup", "competencyGroup", JoinType.INNER_JOIN);
        criteria.createAlias("competencyGroup.competencyType", "competencyType", JoinType.INNER_JOIN);
		
		if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCompetencyGroupName())){
            criteria.add(Restrictions.like("competencyGroup.name", parameter.getCompetencyGroupName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCompetencyTypeName())){
            criteria.add(Restrictions.like("competencyType.name", parameter.getCompetencyTypeName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public AppraisalCompetencyUnit getEntityByIdWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		return (AppraisalCompetencyUnit) criteria.uniqueResult();
	}

}
