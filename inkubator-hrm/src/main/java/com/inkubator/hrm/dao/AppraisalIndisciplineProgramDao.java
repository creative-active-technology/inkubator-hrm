package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AppraisalIndisciplineProgram;

/**
 *
 * @author rizkykojek
 */
public interface AppraisalIndisciplineProgramDao extends IDAO<AppraisalIndisciplineProgram> {

	public AppraisalIndisciplineProgram getEntityByAppraisalProgramIdAndDisciplineTypeId(Long appraisalProgramId, Long disciplineTypeId);
	
	public void deleteByAppraisalProgramId(Long appraisalProgramId);
	
}
