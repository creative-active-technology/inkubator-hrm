package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorDao;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalPerformanceIndicatorDao")
@Lazy
public class AppraisalPerformanceIndicatorDaoImpl extends IDAOImpl<AppraisalPerformanceIndicator> implements AppraisalPerformanceIndicatorDao {

	@Override
	public Class<AppraisalPerformanceIndicator> getEntityClass() {
		return AppraisalPerformanceIndicator.class;
	}

	@Override
	public List<AppraisalPerformanceIndicator> getListByIdAppraisalPerformanceGroup(Long idAppraisalPerformanceGroup) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("performanceGroup", FetchMode.JOIN);
        criteria.setFetchMode("systemScoring", FetchMode.JOIN);
        criteria.add(Restrictions.eq("performanceGroup.id", idAppraisalPerformanceGroup));
        return criteria.list();
	}

	@Override
	public AppraisalPerformanceIndicator getEntityWithDetailById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("performanceGroup", FetchMode.JOIN);
        criteria.setFetchMode("systemScoring", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (AppraisalPerformanceIndicator) criteria.uniqueResult();
	}

}
