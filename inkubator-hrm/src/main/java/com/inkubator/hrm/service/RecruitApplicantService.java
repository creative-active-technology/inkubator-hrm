package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.model.ApplicantModel;
import com.inkubator.hrm.web.model.ApplicantStatisticViewModel;
import com.inkubator.hrm.web.model.ApplicantUploadBatchModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;

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

}
