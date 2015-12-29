package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalProgramDao;
import com.inkubator.hrm.entity.AppraisalProgram;

/**
 *
 * @author rizkykojek
 */

@Repository(value = "appraisalProgramDao")
@Lazy
public class AppraisalProgramDaoImpl extends IDAOImpl<AppraisalProgram> implements AppraisalProgramDao {

	@Override
	public Class<AppraisalProgram> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalProgram.class;
	}

}
