package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;


/**
 * 
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationInstallmentDao extends IDAO<LoanNewApplicationInstallment> {

//	public List<LoanNewApplication> getByParam(CitySearchParameter parameter, int firstResult, int maxResults, Order orderable);
//
//	public Long getTotalCityByParam(CitySearchParameter parameter);

	public Long getTotalByCode(String name);

	public Long getTotalByCodeAndNotId(String code, Long id);

	public LoanNewApplicationInstallment getEntityByIdWithDetail(Long id);	
	
	public Long getTotalInstallmentByLoanNewApplicationId(Integer loanNewApplicationId);

	public List<LoanNewApplicationInstallment> getAllDataDisbursedByEmpDataIdAndLoanTypeIdAndPeriodDate(Long empDataId, Long loanTypeId, Date startPeriodDate, Date endPeriodDate);
	
	public List<LoanNewApplicationInstallment> getListByLoanNewApplicationId(Integer loanNewApplicationId);
	
	public LoanNewApplicationInstallment getLastPaidTerminInstallment(Integer LoanNewApplicationid);
}
