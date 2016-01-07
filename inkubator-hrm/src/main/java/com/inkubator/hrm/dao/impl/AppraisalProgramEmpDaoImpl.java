package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalProgramEmpDao;
import com.inkubator.hrm.entity.AppraisalProgramEmp;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalProgramEmpDao")
@Lazy
public class AppraisalProgramEmpDaoImpl extends IDAOImpl<AppraisalProgramEmp> implements AppraisalProgramEmpDao {

	@Override
	public Class<AppraisalProgramEmp> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalProgramEmp.class;
	}

}
