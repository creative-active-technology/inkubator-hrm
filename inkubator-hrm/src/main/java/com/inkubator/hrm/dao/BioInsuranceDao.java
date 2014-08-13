package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioInsurance;
import com.inkubator.hrm.web.search.BioInsuranceSearchParameter;

/**
 *
 * @author Taufik hidayat
 */
public interface BioInsuranceDao extends IDAO<BioInsurance> {

    public List<BioInsurance> getAllDataByBioDataId(Long bioDataId);

    public Long getTotalByNoPolicy(String noPolicy);

    public Long getTotalByNoPolicyAndNotId(String noPolicy, Long id);

}
