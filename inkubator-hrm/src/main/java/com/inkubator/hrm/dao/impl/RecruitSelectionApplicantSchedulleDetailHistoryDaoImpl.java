package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailHistoryDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailHistory;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value = "recruitSelectionApplicantSchedulleDetailHistoryDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailHistoryDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulleDetailHistory> implements RecruitSelectionApplicantSchedulleDetailHistoryDao{

	@Override
	public Class<RecruitSelectionApplicantSchedulleDetailHistory> getEntityClass() {
		return RecruitSelectionApplicantSchedulleDetailHistory.class;
	}

}
