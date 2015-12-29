package com.inkubator.hrm.dao.impl;

import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.entity.AppraisalIndisciplineProgram;

/**
 *
 * @author rizkykojek
 */

@Repository(value = "appraisalIndisciplineProgramDao")
public class AppraisalIndisciplineProgramDaoImpl extends IDAOImpl<AppraisalIndisciplineProgram>
		implements IDAO<AppraisalIndisciplineProgram> {

	@Override
	public Class<AppraisalIndisciplineProgram> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalIndisciplineProgram.class;
	}

}
