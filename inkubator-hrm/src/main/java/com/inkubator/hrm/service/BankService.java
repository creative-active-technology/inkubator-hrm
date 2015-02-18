package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.web.search.BankSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface BankService extends IService<Bank> {

	public List<Bank> getByParam(BankSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(BankSearchParameter parameter) throws Exception;
        
        public Bank getEntityWithDetail(Long id) throws Exception;

}
