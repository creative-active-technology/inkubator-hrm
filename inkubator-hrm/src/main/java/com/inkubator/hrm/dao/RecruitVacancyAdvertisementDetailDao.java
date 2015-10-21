package com.inkubator.hrm.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.web.search.VacancySearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitVacancyAdvertisementDetailDao extends IDAO<RecruitVacancyAdvertisementDetail> {

	public void deleteByVacancyAdvertisementId(Long vacancyAdvertisementId);

	public List<RecruitVacancyAdvertisementDetail> getAllDataVacancyByParam(VacancySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalVacancyByParam(VacancySearchParameter parameter);

	public RecruitVacancyAdvertisementDetail getEntityByPkWithDetail(Long id);
	
	public RecruitVacancyAdvertisementDetail getEntityByRecruitHireApplyId(Long recruitHireApplyId);

	public List<RecruitVacancyAdvertisementDetail> getAllDataByVacancyAdvertisementIdWithDetail(Long vacancyAdvertisementId);

	public void deleteAll(Collection<RecruitVacancyAdvertisementDetail> listEntity);

	public void saveAll(Collection<RecruitVacancyAdvertisementDetail> list);
	
}
