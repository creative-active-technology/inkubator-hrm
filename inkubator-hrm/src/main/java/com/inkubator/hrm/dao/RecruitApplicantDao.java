package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.model.ApplicantAgeViewModel;
import com.inkubator.hrm.web.model.ApplicantViewModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitApplicantDao extends IDAO<RecruitApplicant> {

	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalByParam(RecruitApplicantSearchParameter parameter);

	public RecruitApplicant getEntityByPkWithDetail(Long id);
	
	public List<ApplicantViewModel> getDataChartEducationLevel();
	
	public List<ApplicantViewModel> getDataChartJobClassification();
	
	public ApplicantViewModel getDataChartWorkingExperience();

	public List<ApplicantAgeViewModel> getDataChartAge();
	
	public Long getTotalByCareerCandidateAndOrgTypeOfSpecId(Integer careerCandidate, Long specId);

}
