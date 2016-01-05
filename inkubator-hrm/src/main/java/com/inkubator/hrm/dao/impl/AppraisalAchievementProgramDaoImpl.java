package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalAchievementProgramDao;
import com.inkubator.hrm.entity.AppraisalAchievementProgram;

/**
 *
 * @author rizkykojek
 */

@Repository(value = "appraisalAchievementProgramDao")
@Lazy
public class AppraisalAchievementProgramDaoImpl extends IDAOImpl<AppraisalAchievementProgram>
		implements AppraisalAchievementProgramDao {

	@Override
	public Class<AppraisalAchievementProgram> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalAchievementProgram.class;
	}

	@Override
	public AppraisalAchievementProgram getEntityByAppraisalProgramIdAndAwardTypeId(Long appraisalProgramId, Long awardTypeId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("appraisalProgram.id", appraisalProgramId));
		criteria.add(Restrictions.eq("careerAwardType.id", awardTypeId));
		return (AppraisalAchievementProgram) criteria.uniqueResult();
	}

	@Override
	public void deleteByAppraisalProgramId(Long appraisalProgramId) {
		Query query = getCurrentSession().createQuery("DELETE FROM AppraisalAchievementProgram temp WHERE temp.appraisalProgram.id = :appraisalProgramId")
				.setLong("appraisalProgramId", appraisalProgramId);
        query.executeUpdate();
		
	}

}
