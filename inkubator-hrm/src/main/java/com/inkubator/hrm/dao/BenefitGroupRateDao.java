package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BenefitGroupRate;

/**
 *
 * @author Taufik Hidayat
 */
public interface BenefitGroupRateDao extends IDAO<BenefitGroupRate> {

    public List<BenefitGroupRate> getAllDataByBenefitGroupId(Long benefitGroupId);

    public BenefitGroupRate getEntityByPKWithDetail(Long id);
    
    public Long getTotalByBenefitGroupAndGolonganJabatan(Long benefitId, Long golonganId);
    
    public Long getTotalByBenefitGroupAndGolonganJabatanAndNotId(Long benefitId, Long golonganId, Long Id);
    
    public List<BenefitGroupRate> getByGolonganJabatan(Long golonganId);
    
    public List<BenefitGroupRate> getAllDataByBenefitGroupIdAndGolJabatanId(Long benefitGroupId, Long golJabatanId);
}
