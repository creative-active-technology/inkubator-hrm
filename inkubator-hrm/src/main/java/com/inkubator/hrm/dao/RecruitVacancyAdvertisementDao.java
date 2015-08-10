package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.web.search.VacancyAdvertisementSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitVacancyAdvertisementDao extends IDAO<RecruitVacancyAdvertisement> {
	
	public List<RecruitVacancyAdvertisement> getByParam(VacancyAdvertisementSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(VacancyAdvertisementSearchParameter parameter);

	public RecruitVacancyAdvertisement getEntityByPkWithDetail(Long id);

	public Long getCurrentMaxId();
}
