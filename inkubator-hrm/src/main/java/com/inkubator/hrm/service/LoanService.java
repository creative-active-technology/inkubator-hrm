package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.web.search.LoanSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LoanService extends IService<Loan> {
	
	public List<Loan> getByParam(LoanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(LoanSearchParameter parameter) throws Exception;

}
