package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FinancialPartner;

/**
 *
 * @author rizkykojek
 */
public interface FinancialPartnerDao extends IDAO<FinancialPartner> {

	public List<FinancialPartner> getAllDataByCompanyId(Long companyId);

	public FinancialPartner getEntityByPKWithDetail(Long id);
	
	public Long getTotalByAccountNumber(Integer accountNumber);

    public Long getTotalByAccountNumberAndNotId(Integer accountNumber, Long id);
	
}
