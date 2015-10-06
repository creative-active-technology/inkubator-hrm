package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitApplicantSpecList;

/**
 *
 * @author rizkykojek
 */
public interface RecruitApplicantSpecListDao extends IDAO<RecruitApplicantSpecList> {

	public void deleteByApplicantId(Long applicantId);

}
