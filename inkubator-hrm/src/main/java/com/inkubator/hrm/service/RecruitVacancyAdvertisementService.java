package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.web.search.VacancyAdvertisementSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitVacancyAdvertisementService extends IService<RecruitVacancyAdvertisement>, BaseApprovalService {

	//functions
	public String saveWithApproval(RecruitVacancyAdvertisement entity) throws Exception;
		
	public String saveWithRevised(RecruitVacancyAdvertisement entity, Long approvalActivityId) throws Exception;

	public List<RecruitVacancyAdvertisement> getByParam(VacancyAdvertisementSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception;

	public Long getTotalByParam(VacancyAdvertisementSearchParameter parameter) throws Exception;

	public RecruitVacancyAdvertisement getEntityByPkWithDetail(Long id) throws Exception;
}
