package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.web.search.BankSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface BankDao extends IDAO<Bank> {

	public List<Bank> getByParam(BankSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalBankByParam(BankSearchParameter parameter);
	
	public Long getTotalByCode(String name);
	
	public Long getTotalByCodeAndNotId(String code, Long id);

}
