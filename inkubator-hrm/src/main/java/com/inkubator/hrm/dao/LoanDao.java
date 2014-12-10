package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.web.search.LoanSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface LoanDao extends IDAO<Loan> {

	public List<Loan> getByParam(LoanSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(LoanSearchParameter parameter);

	public Loan getEntityByPkWithDetail(Long id);

	public Loan getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber);
	
	public List<Loan> getAllDataByEmpDataId(Long empDataId);
}
