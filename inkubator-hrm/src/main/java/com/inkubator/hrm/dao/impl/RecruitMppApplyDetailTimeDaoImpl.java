package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitMppApplyDetailTimeDao;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value = "recruitMppApplyDetailTimeDao")
@Lazy
public class RecruitMppApplyDetailTimeDaoImpl extends IDAOImpl<RecruitMppApplyDetailTime> implements RecruitMppApplyDetailTimeDao {

	@Override
	public Class<RecruitMppApplyDetailTime> getEntityClass() {
		return RecruitMppApplyDetailTime.class;
	}

}
