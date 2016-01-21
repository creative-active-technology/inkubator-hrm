package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorJabatanDao;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicatorJabatan;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalPerformanceIndicatorJabatanDao")
@Lazy
public class AppraisalPerformanceIndicatorJabatanDaoImpl extends IDAOImpl<AppraisalPerformanceIndicatorJabatan> implements AppraisalPerformanceIndicatorJabatanDao {

	@Override
	public Class<AppraisalPerformanceIndicatorJabatan> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalPerformanceIndicatorJabatan.class;
	}

}
