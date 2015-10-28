package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.model.ApplicantModel;
import com.inkubator.hrm.web.model.ApplicantRealizationViewModel;
import com.inkubator.hrm.web.model.ApplicantStatisticViewModel;
import com.inkubator.hrm.web.model.ApplicantUploadBatchModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;
import com.inkubator.hrm.web.search.RecruitInitialSelectionSearchParameter;
import com.inkubator.hrm.web.search.ReportSearchRecruitmentSearchParameter;
import com.inkubator.hrm.web.search.SelectionApplicantRealizationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitApplicantService extends IService<RecruitApplicant> {

	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception;

	public Long getTotalByParam(RecruitApplicantSearchParameter parameter) throws Exception;
	
	public RecruitApplicant getEntityByPkWithDetail(Long id) throws Exception;

	public RecruitApplicant update(ApplicantModel model) throws Exception;

	public RecruitApplicant save(ApplicantModel model) throws Exception;
	
	public void uploadBatchProcess(ApplicantUploadBatchModel model) throws Exception;
	
	public ApplicantStatisticViewModel getAllDataApplicantStatistic() throws Exception;
	
	public String commitDataInternalCareerCandidate(List<Long> listEmpDataId) throws Exception;

	public Long getTotalByVacancyAdvertisementDetailId(Long vacancyAdvertisementDetailId);

    public List<RecruitApplicant> getByParamForReportSearchRecruitment(ReportSearchRecruitmentSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);
        
    public Long getTotalByParamforReportSearchRecruitment(ReportSearchRecruitmentSearchParameter searchParameter);
    
    public List<RecruitApplicant> getAllDataByParamWithDetail(RecruitInitialSelectionSearchParameter parameter, int firstResults, int maxResults, Order orderable) throws Exception;

	public Long getTotalDataByParamWithDetail(RecruitInitialSelectionSearchParameter parameter) throws Exception;
	
	public List<ApplicantRealizationViewModel> getSelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalSelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter);
	
}
