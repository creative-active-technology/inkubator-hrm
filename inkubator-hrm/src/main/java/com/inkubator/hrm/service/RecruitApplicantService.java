package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RecruitApplicantService extends IService<RecruitApplicant> {

	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception;

	public Long getTotalByParam(RecruitApplicantSearchParameter parameter) throws Exception;
	
	public RecruitApplicant getEntityByPkWithDetail(Long id) throws Exception;

}
