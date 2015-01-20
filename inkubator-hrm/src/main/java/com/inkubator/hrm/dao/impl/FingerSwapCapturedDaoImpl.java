package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FingerSwapCapturedDao;
import com.inkubator.hrm.entity.FingerSwapCaptured;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "fingerSwapCapturedDao")
@Lazy
public class FingerSwapCapturedDaoImpl extends IDAOImpl<FingerSwapCaptured> implements FingerSwapCapturedDao {

	@Override
	public Class<FingerSwapCaptured> getEntityClass() {
		return FingerSwapCaptured.class;
		
	}

	
}
