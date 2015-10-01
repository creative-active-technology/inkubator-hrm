package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitApplicantDao extends IDAO<RecruitApplicant> {

	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalByParam(RecruitApplicantSearchParameter parameter);

}
