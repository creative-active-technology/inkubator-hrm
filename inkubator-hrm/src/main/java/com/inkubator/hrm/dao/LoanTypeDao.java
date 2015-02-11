package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.web.search.BankSearchParameter;
import com.inkubator.hrm.web.search.LoanTypeSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LoanTypeDao extends IDAO<LoanType> {

	public List<LoanType> getByParam(LoanTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalLoanTypeByParam(LoanTypeSearchParameter parameter);
        
        public Long getTotalByLoanTypeCode(String loanTypeCode);
        
        public Long getTotalByLoanTypeName(String loanTypeName);
        
        public LoanType getEntityWithRelationByPk(Long id);
		
        public void saveAndMerge(LoanType loanType);
}
