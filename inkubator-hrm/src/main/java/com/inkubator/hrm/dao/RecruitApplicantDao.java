package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.model.ApplicantAgeViewModel;
import com.inkubator.hrm.web.model.ApplicantRealizationViewModel;
import com.inkubator.hrm.web.model.ApplicantViewModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;
import com.inkubator.hrm.web.search.RecruitInitialSelectionSearchParameter;
import com.inkubator.hrm.web.search.ReportSearchRecruitmentSearchParameter;
import com.inkubator.hrm.web.search.SelectionApplicantRealizationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitApplicantDao extends IDAO<RecruitApplicant> {

	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalByParam(RecruitApplicantSearchParameter parameter);
	
	public List<RecruitApplicant> getAllDataByParamWithDetail(RecruitInitialSelectionSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalDataByParamWithDetail(RecruitInitialSelectionSearchParameter parameter);

	public RecruitApplicant getEntityByPkWithDetail(Long id);
	
	public RecruitApplicant getEntityByPkWithHireApplyDetail(Long id);
	
	public List<ApplicantViewModel> getDataChartEducationLevel();
	
	public List<ApplicantViewModel> getDataChartJobClassification();
	
	public ApplicantViewModel getDataChartWorkingExperience();

	public List<ApplicantAgeViewModel> getDataChartAge();
	
	public Long getTotalByCareerCandidateAndOrgTypeOfSpecId(Integer careerCandidate, Long specId);

	public Long getTotalByVacancyAdvertisementDetailId(Long vacancyAdvertisementDetailId);

    public List<RecruitApplicant> getByParamForReportSearchRecruitment(ReportSearchRecruitmentSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);
        
    public Long getTotalByParamforReportSearchRecruitment(ReportSearchRecruitmentSearchParameter searchParameter);
    
    public List<ApplicantRealizationViewModel> getSelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalSelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter);
}
 