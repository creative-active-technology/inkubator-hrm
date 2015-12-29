package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.entity.AppraisalAchievementProgram;

/**
 *
 * @author rizkykojek
 */

@Repository(value = "appraisalAchievementProgramDao")
@Lazy
public class AppraisalAchievementProgramDaoImpl extends IDAOImpl<AppraisalAchievementProgram>
		implements IDAO<AppraisalAchievementProgram> {

	@Override
	public Class<AppraisalAchievementProgram> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalAchievementProgram.class;
	}

}
