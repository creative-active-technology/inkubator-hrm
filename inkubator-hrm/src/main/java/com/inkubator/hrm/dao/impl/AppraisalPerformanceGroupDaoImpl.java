package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalPerformanceGroupDao;
import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.web.search.AppraisalPerformanceGroupSearchParameter;

@Repository(value = "appraisalPerformanceGroupDao")
@Lazy
public class AppraisalPerformanceGroupDaoImpl extends IDAOImpl<AppraisalPerformanceGroup> implements AppraisalPerformanceGroupDao{

	@Override
	public List<AppraisalPerformanceGroup> getByParam(AppraisalPerformanceGroupSearchParameter searchParameter,
			int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(AppraisalPerformanceGroupSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(AppraisalPerformanceGroupSearchParameter searchParameter, Criteria criteria){
		if(searchParameter.getCode() != null){
			criteria.add(Restrictions.ilike("code", searchParameter.getCode(), MatchMode.START));
		}
		if(searchParameter.getName() != null){
			criteria.add(Restrictions.ilike("name", searchParameter.getName(), MatchMode.START));
		}
	}

	@Override
	public Class<AppraisalPerformanceGroup> getEntityClass() {
		return AppraisalPerformanceGroup.class;
	}

	@Override
	public List<AppraisalPerformanceGroup> getAllDataFetchPerformanceIndicatorAndScoringIndex() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("appraisalPerformanceIndicators", FetchMode.JOIN);
		criteria.setFetchMode("appraisalPerformanceIndicators.systemScoring", FetchMode.JOIN);
		criteria.setFetchMode("appraisalPerformanceIndicators.systemScoring.systemScoringIndexes", FetchMode.JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
