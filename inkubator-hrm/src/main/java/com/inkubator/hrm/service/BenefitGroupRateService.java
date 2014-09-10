package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BenefitGroupRate;

/**
*
* @author Taufik Hidayat
*/
public interface BenefitGroupRateService extends IService<BenefitGroupRate> {

	public List<BenefitGroupRate> getAllDataByBenefitGroupId(Long benefitGroupId) throws Exception;
        
        public BenefitGroupRate getEntityByPKWithDetail(Long id) throws Exception;
        
        public List<BenefitGroupRate> getByGolonganJabatan(Long golonganId) throws Exception;

}
