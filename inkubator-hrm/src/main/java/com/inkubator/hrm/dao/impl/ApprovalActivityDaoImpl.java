package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.entity.ApprovalActivity;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "approvalActivityDao")
@Lazy
public class ApprovalActivityDaoImpl extends IDAOImpl<ApprovalActivity> implements ApprovalActivityDao {

	@Override
	public Class<ApprovalActivity> getEntityClass() {
		return ApprovalActivity.class;
		
	}

	
}
