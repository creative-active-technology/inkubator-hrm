package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailRealizationDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailRealization;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitSelectionApplicantSchedulleDetailRealizationDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailRealizationDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulleDetailRealization> 
	implements RecruitSelectionApplicantSchedulleDetailRealizationDao {

	@Override
	public Class<RecruitSelectionApplicantSchedulleDetailRealization> getEntityClass() {
		// TODO Auto-generated method stub
		return RecruitSelectionApplicantSchedulleDetailRealization.class;
	}

	@Override
	public List<RecruitSelectionApplicantSchedulleDetailRealization> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
			Long applicantId, Long selectionApplicantSchedulleId) {
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		
		criteria.setFetchMode("scoringByEmpData", FetchMode.JOIN);
		criteria.setFetchMode("scoringByEmpData.bioData", FetchMode.JOIN);
		
		criteria.createAlias("recruitSelectionApplicantSchedulleDetail", "recruitSelectionApplicantSchedulleDetail", JoinType.INNER_JOIN);
		criteria.createAlias("recruitSelectionApplicantSchedulleDetail.applicant", "applicant", JoinType.INNER_JOIN);
		criteria.createAlias("recruitSelectionApplicantSchedulleDetail.recruitSelectionApplicantSchedulle", "recruitSelectionApplicantSchedulle", JoinType.INNER_JOIN);
		
		criteria.add(Restrictions.eq("applicant.id", applicantId));
		criteria.add(Restrictions.eq("recruitSelectionApplicantSchedulle.id", selectionApplicantSchedulleId));
		return criteria.list();
	}

	@Override
	public RecruitSelectionApplicantSchedulleDetailRealization getEntityBySelectionApplicantSchedulleDetailId(Long selectionApplicantSchedulleDetailId) {
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("scoringByEmpData", FetchMode.JOIN);
		criteria.setFetchMode("scoringByEmpData.bioData", FetchMode.JOIN);
		criteria.add(Restrictions.eq("recruitSelectionApplicantSchedulleDetail.id", selectionApplicantSchedulleDetailId));
		
		return (RecruitSelectionApplicantSchedulleDetailRealization) criteria.uniqueResult();
	}

	@Override
	public Boolean isSchedulleDetailHaveBeenRealized(Long recruitSelectionApplicantScheduleDetailId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("recruitSelectionApplicantSchedulleDetail", "schedulleDetail", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("schedulleDetail.id", recruitSelectionApplicantScheduleDetailId));
		return !criteria.list().isEmpty();
	}

}
