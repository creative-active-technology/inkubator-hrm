package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CompanyBankAccount;

/**
 *
 * @author rizkykojek
 */
public interface CompanyBankAccountService extends IService<CompanyBankAccount> {

	public List<CompanyBankAccount> getAllDataByCompanyId(Long companyId) throws Exception;

	public CompanyBankAccount getEntityByPKWithDetail(Long id);
	
}
