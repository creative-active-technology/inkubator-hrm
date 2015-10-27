package com.inkubator.hrm.dao.impl;

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

}
