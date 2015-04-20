package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.LoanNewDisbursement;
import com.inkubator.hrm.web.search.BankSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LoanNewDisbursementDao extends IDAO<LoanNewDisbursement> {

//	public List<LoanNewDisbursement> getByParam(BankSearchParameter parameter, int firstResult, int maxResults, Order orderable);
//
//	public Long getTotalByParam(BankSearchParameter parameter);
	
	
        public LoanNewDisbursement getEntityWithDetail(Long id);
        
        public Long getCurrentMaxId();
}
