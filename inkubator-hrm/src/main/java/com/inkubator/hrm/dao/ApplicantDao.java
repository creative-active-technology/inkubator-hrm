package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Applicant;
import com.inkubator.hrm.web.search.ApplicantSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface ApplicantDao extends IDAO<Applicant> {

	public List<Applicant> getByParam(ApplicantSearchParameter parameter, int firstResults, int maxResults, Order orderable);

	public Long getTotalByParam(ApplicantSearchParameter parameter);

}
