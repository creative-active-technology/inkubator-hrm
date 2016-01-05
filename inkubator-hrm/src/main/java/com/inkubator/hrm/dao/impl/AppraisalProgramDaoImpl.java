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
import com.inkubator.hrm.dao.AppraisalProgramDao;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;

/**
 *
 * @author rizkykojek
 */

@Repository(value = "appraisalProgramDao")
@Lazy
public class AppraisalProgramDaoImpl extends IDAOImpl<AppraisalProgram> implements AppraisalProgramDao {

	@Override
	public Class<AppraisalProgram> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalProgram.class;
	}

	@Override
	public List<AppraisalProgram> getAllDataByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(AppraisalProgramSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchByParam(AppraisalProgramSearchParameter searchParameter, Criteria criteria) {
		if (StringUtils.isNotEmpty(searchParameter.getName())) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        }
		criteria.add(Restrictions.isNotNull("id"));
		
	}

	@Override
	public AppraisalProgram getEntityByIdWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("appraisalAchievementPrograms", FetchMode.JOIN);
		criteria.setFetchMode("appraisalIndisciplinePrograms", FetchMode.JOIN);
		return (AppraisalProgram) criteria.uniqueResult();
	}

}
