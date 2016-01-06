package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalIndisciplineProgramDao;
import com.inkubator.hrm.entity.AppraisalIndisciplineProgram;

/**
 *
 * @author rizkykojek
 */

@Repository(value = "appraisalIndisciplineProgramDao")
@Lazy
public class AppraisalIndisciplineProgramDaoImpl extends IDAOImpl<AppraisalIndisciplineProgram>
		implements AppraisalIndisciplineProgramDao {

	@Override
	public Class<AppraisalIndisciplineProgram> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalIndisciplineProgram.class;
	}

	@Override
	public AppraisalIndisciplineProgram getEntityByAppraisalProgramIdAndDisciplineTypeId(Long appraisalProgramId, Long disciplineTypeId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("appraisalProgram.id", appraisalProgramId));
		criteria.add(Restrictions.eq("careerDisciplineType.id", disciplineTypeId));
		return (AppraisalIndisciplineProgram) criteria.uniqueResult();
	}

	@Override
	public void deleteByAppraisalProgramId(Long appraisalProgramId) {
		Query query = getCurrentSession().createQuery("DELETE FROM AppraisalIndisciplineProgram temp WHERE temp.appraisalProgram.id = :appraisalProgramId")
				.setLong("appraisalProgramId", appraisalProgramId);
        query.executeUpdate();
		
	}

}
