package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantPassedDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitSelectionApplicantPassedDao")
@Lazy
public class RecruitSelectionApplicantPassedDaoImpl extends IDAOImpl<RecruitSelectionApplicantPassed>
        implements RecruitSelectionApplicantPassedDao {

    @Override
    public Class<RecruitSelectionApplicantPassed> getEntityClass() {
        // TODO Auto-generated method stub
        return RecruitSelectionApplicantPassed.class;
    }

    @Override
    public List<RecruitSelectionApplicantPassed> getAllWithPlacementStatus(String status) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("placementStatus", status));
        return criteria.list();
    }

	@Override
	public RecruitSelectionApplicantPassed getEntityByPk(RecruitSelectionApplicantPassedId id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		return (RecruitSelectionApplicantPassed) criteria.uniqueResult();
	}

	@Override
	public Long getTotalByHireApplyIdAndPlacementStatus(Long hireApplyId, String placementStatus) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("hireApply.id", hireApplyId));
		criteria.add(Restrictions.not(Restrictions.eq("placementStatus", placementStatus)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
