package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalSystemScoringIndexDao;
import com.inkubator.hrm.entity.AppraisalSystemScoringIndex;
import com.inkubator.hrm.entity.HrmUser;

@Repository(value = "appraisalSystemScoringIndexDao")
@Lazy
public class AppraisalSystemScoringIndexDaoImpl extends IDAOImpl<AppraisalSystemScoringIndex> implements AppraisalSystemScoringIndexDao {

	@Override
	public Class<AppraisalSystemScoringIndex> getEntityClass() {
		return AppraisalSystemScoringIndex.class;
	}

	@Override
	public List<AppraisalSystemScoringIndex> getAllDataByAppraisalSystemScoringId(Long appraisalSystemScoringId) {
		System.out.println(appraisalSystemScoringId + " id dari dao");
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("appraisalSystemScoring", "appraisalSystemScoring", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("appraisalSystemScoring.id", appraisalSystemScoringId));
		return criteria.list();
	}

	
}
