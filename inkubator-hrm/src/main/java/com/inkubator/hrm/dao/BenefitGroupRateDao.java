package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BenefitGroupRate;

/**
 *
 * @author Taufik Hidayat
 */
public interface BenefitGroupRateDao extends IDAO<BenefitGroupRate> {

    public List<BenefitGroupRate> getAllDataByBenefitGroupId(Long benefitGroupId);

    public BenefitGroupRate getEntityByPKWithDetail(Long id);

}
