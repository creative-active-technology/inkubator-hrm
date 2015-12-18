package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalCompetencyUnitIndicatorDao;
import com.inkubator.hrm.entity.AppraisalCompetencyUnitIndicator;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalCompetencyUnitIndicatorDao")
@Lazy
public class AppraisalCompetencyUnitIndicatorDaoImpl extends IDAOImpl<AppraisalCompetencyUnitIndicator> implements AppraisalCompetencyUnitIndicatorDao {

	@Override
	public Class<AppraisalCompetencyUnitIndicator> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalCompetencyUnitIndicator.class;
	}

}
