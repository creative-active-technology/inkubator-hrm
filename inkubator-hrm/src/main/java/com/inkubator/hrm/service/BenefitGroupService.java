package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.web.model.BenefitGroupModel;
import com.inkubator.hrm.web.model.BenefitGroupRenumerationModel;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public interface BenefitGroupService extends IService<BenefitGroup> {

    public List<BenefitGroup> getByParam(BenefitGroupSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(BenefitGroupSearchParameter parameter) throws Exception;

    public String getBenefitGroupNameByPk(Long id) throws Exception;
    
    public List<BenefitGroupRenumerationModel> getAllDataRenumeration(Long empDataId) throws Exception;
    
}
