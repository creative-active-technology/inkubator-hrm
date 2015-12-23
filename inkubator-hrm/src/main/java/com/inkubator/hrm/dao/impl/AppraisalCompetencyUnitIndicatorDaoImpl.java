package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyUnitIndicatorDao;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalCompetencyUnitIndicatorDao")
@Lazy
public class AppraisalCompetencyUnitIndicatorDaoImpl extends IDAOImpl<AppraisalCompetencyUnitIndicator> implements AppraisalCompetencyUnitIndicatorDao {

	@Override
	public Class<AppraisalCompetencyUnitIndicator> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalCompetencyUnitIndicator.class;
	}

	@Override
	public List<AppraisalCompetencyUnitIndicator> getAllDataByCompetencyUnitId(Long competencyUnitId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("appraisalCompetencyUnit.id", competencyUnitId));
		return criteria.list();
	}

	@Override
	public AppraisalCompetencyUnitIndicator getEntityByIdWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("appraisalCompetencyUnit", FetchMode.JOIN);
		criteria.setFetchMode("appraisalCompetencyUnit.competencyGroup", FetchMode.JOIN);
		criteria.setFetchMode("appraisalCompetencyUnit.competencyGroup.competencyType", FetchMode.JOIN);
		return (AppraisalCompetencyUnitIndicator) criteria.uniqueResult();
	}

	@Override
	public Long getTotalByIndicatorAndCompetencyUnitId(String indicator, Long competencyUnitId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("indicator", indicator));
        criteria.add(Restrictions.eq("appraisalCompetencyUnit.id", competencyUnitId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByIndicatorAndCompetencyUnitIdAndNotId(String indicator, Long competencyUnitId, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("indicator", indicator));
        criteria.add(Restrictions.eq("appraisalCompetencyUnit.id", competencyUnitId));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByLevelIndexAndCompetencyUnitId(Integer levelIndex, Long competencyUnitId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("levelIndex", levelIndex));
        criteria.add(Restrictions.eq("appraisalCompetencyUnit.id", competencyUnitId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getTotalByLevelIndexAndCompetencyUnitIdAndNotId(Integer levelIndex, Long competencyUnitId, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("levelIndex", levelIndex));
        criteria.add(Restrictions.eq("appraisalCompetencyUnit.id", competencyUnitId));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
