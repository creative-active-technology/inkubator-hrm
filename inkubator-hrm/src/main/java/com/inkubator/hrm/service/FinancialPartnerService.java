package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.FinancialPartner;

/**
 *
 * @author rizkykojek
 */
public interface FinancialPartnerService extends IService<FinancialPartner> {

	public List<FinancialPartner> getAllDataByCompanyId(Long companyId);

	public FinancialPartner getEntityByPKWithDetail(Long id);

}
