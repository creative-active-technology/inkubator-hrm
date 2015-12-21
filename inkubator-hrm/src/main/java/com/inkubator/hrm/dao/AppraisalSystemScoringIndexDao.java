package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalSystemScoringIndex;

public interface AppraisalSystemScoringIndexDao extends IDAO<AppraisalSystemScoringIndex>{
	public List<AppraisalSystemScoringIndex> getAllDataByAppraisalSystemScoringId(Long appraisalSystemScoringId);

}
