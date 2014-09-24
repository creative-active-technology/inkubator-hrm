package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionLeaveDao;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;

/**
 *
 * @author rizkykojek
 */
@Repository
@Lazy
public class ApprovalDefinitionLeaveDaoImpl extends IDAOImpl<ApprovalDefinitionLeave> implements ApprovalDefinitionLeaveDao {

	@Override
	public Class<ApprovalDefinitionLeave> getEntityClass() {
		return ApprovalDefinitionLeave.class;
		
	}

	

}
