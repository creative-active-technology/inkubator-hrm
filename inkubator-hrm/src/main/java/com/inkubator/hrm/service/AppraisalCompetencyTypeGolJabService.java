package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJab;

/**
* @author Ahmad Mudzakkir Amal
*/
public interface AppraisalCompetencyTypeGolJabService extends IService<AppraisalCompetencyTypeGolJab> {
	public List<AppraisalCompetencyTypeGolJab> getListByAppraisalCompetenceTypeId(Long appraisalCompetenceTypeId) throws Exception;
}
