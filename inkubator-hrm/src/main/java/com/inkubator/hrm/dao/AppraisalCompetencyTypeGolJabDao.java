package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalCompetencyTypeGolJab;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface AppraisalCompetencyTypeGolJabDao extends IDAO<AppraisalCompetencyTypeGolJab> {
	public List<AppraisalCompetencyTypeGolJab> getListByAppraisalCompetenceTypeId(Long appraisalCompetenceTypeId);
}
