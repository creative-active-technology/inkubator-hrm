package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorDao;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicator;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalPerformanceIndicatorDao")
@Lazy
public class AppraisalPerformanceIndicatorDaoImpl extends IDAOImpl<AppraisalPerformanceIndicator> implements AppraisalPerformanceIndicatorDao {

	@Override
	public Class<AppraisalPerformanceIndicator> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalPerformanceIndicator.class;
	}

}
