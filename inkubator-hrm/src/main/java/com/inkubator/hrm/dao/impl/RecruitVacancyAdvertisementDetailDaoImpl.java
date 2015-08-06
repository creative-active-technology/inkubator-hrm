package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDetailDao;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitVacancyAdvertisementDetailDao")
@Lazy
public class RecruitVacancyAdvertisementDetailDaoImpl extends IDAOImpl<RecruitVacancyAdvertisementDetail> implements
		RecruitVacancyAdvertisementDetailDao {

	@Override
	public Class<RecruitVacancyAdvertisementDetail> getEntityClass() {
		return RecruitVacancyAdvertisementDetail.class;
	}

	
}
