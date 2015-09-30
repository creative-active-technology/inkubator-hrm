package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Applicant;
import com.inkubator.hrm.web.search.ApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface ApplicantService extends IService<Applicant> {

	public List<Applicant> getByParam(ApplicantSearchParameter parameter, int first, int pageSize, Order orderable) throws Exception;

	public Long getTotalByParam(ApplicantSearchParameter parameter) throws Exception;

}
