package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitApplicantSpecListDao;
import com.inkubator.hrm.entity.RecruitApplicantSpecList;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitApplicantSpecListDao")
@Lazy
public class RecruitApplicantSpecListDaoImpl extends IDAOImpl<RecruitApplicantSpecList>implements RecruitApplicantSpecListDao {

	@Override
	public Class<RecruitApplicantSpecList> getEntityClass() {
		// TODO Auto-generated method stub
		return RecruitApplicantSpecList.class;
	}

	

}
