package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantPassedDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;

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

}
