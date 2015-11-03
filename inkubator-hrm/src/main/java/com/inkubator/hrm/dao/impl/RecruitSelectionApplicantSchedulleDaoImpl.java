package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantSchedulleDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulle> implements RecruitSelectionApplicantSchedulleDao {

    @Override
    public Class<RecruitSelectionApplicantSchedulle> getEntityClass() {
        return RecruitSelectionApplicantSchedulle.class;
    }

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("selectionSeries", FetchMode.JOIN);
		return (RecruitSelectionApplicantSchedulle) criteria.uniqueResult();
	}

	@Override
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("hireApply", "hireApply", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("hireApply.id", hireApplyId));
		return !criteria.list().isEmpty();
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("hireApply", "hireApply", JoinType.INNER_JOIN);
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("hireApply.id", hireApplyId));
		criteria.setFetchMode("selectionSeries", FetchMode.JOIN);
		return (RecruitSelectionApplicantSchedulle) criteria.uniqueResult();
	}

}
