package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioInsurance;
import com.inkubator.hrm.web.search.BioInsuranceSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface BioInsuranceService extends IService<BioInsurance> {

	public List<BioInsurance> getAllDataByBioDataId(Long bioDataId) throws Exception;

}
