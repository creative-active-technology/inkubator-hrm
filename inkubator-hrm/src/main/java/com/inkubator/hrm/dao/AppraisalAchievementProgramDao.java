package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalAchievementProgram;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalAchievementProgramDao extends IDAO<AppraisalAchievementProgram> {

	public AppraisalAchievementProgram getEntityByAppraisalProgramIdAndAwardTypeId(Long appraisalProgramId, Long awardTypeId);
	
	public void deleteByAppraisalProgramId(Long appraisalProgramId);
	
}
