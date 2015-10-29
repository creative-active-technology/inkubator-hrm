package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantSchedulleDetailDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulleDetail> implements RecruitSelectionApplicantSchedulleDetailDao {

    @Override
    public Class<RecruitSelectionApplicantSchedulleDetail> getEntityClass() {
        return RecruitSelectionApplicantSchedulleDetail.class;
    }

	@Override
	public List<RecruitSelectionApplicantSchedulleDetail> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
			Long applicantId, Long selectionApplicantSchedulleId) {
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("applicant", "applicant", JoinType.INNER_JOIN);
		criteria.createAlias("recruitSelectionApplicantSchedulle", "recruitSelectionApplicantSchedulle", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("applicant.id", applicantId));
		criteria.add(Restrictions.eq("recruitSelectionApplicantSchedulle.id", selectionApplicantSchedulleId));
		return criteria.list();
	}

}
