package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CompanyBankAccount;

/**
 *
 * @author rizkykojek
 */
public interface CompanyBankAccountDao extends IDAO<CompanyBankAccount> {

	public List<CompanyBankAccount> getAllDataByCompanyId(Long companyId);

	public CompanyBankAccount getEntityByPKWithDetail(Long id);
	
	public Long getTotalByAccountNumber(Integer accountNumber);

    public Long getTotalByAccountNumberAndNotId(Integer accountNumber, Long id);
	
}
