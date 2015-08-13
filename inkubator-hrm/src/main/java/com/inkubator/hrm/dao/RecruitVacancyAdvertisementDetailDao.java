package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;

/**
 *
 * @author rizkykojek
 */
public interface RecruitVacancyAdvertisementDetailDao extends IDAO<RecruitVacancyAdvertisementDetail> {

	public void deleteByVacancyAdvertisementId(Long vacancyAdvertisementId);

}
