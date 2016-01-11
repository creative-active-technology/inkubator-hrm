package com.inkubator.hrm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalProgramEmpDao;
import com.inkubator.hrm.entity.AppraisalProgramEmp;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.AppraisalProgramEmployeeSearchParameter;

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

	@Override
	public List<EmpData> getAllEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter searchParameter,
			int firstResult, int maxResult, Order order) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public Long getTotalEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter searchParameter) {
		// TODO Auto-generated method stub
		return 0L;
	}

}
