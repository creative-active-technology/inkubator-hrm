package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalSystemScoringIndex;

public interface AppraisalSystemScoringIndexService extends IService<AppraisalSystemScoringIndex>{
	public List<AppraisalSystemScoringIndex> getAllDataByAppraisalSystemScoringId(Long appraisalSystemScoringId) throws Exception;
}
