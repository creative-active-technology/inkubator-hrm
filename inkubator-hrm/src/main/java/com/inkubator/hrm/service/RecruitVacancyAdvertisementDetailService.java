package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.web.search.VacancySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitVacancyAdvertisementDetailService extends IService<RecruitVacancyAdvertisementDetail> {

	public List<RecruitVacancyAdvertisementDetail> getAllDataVacancyByParam(VacancySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalVacancyByParam(VacancySearchParameter parameter);

	public RecruitVacancyAdvertisementDetail getEntityByPkWithDetail(Long id) throws Exception;
	
}
