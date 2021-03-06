/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitDynamicField;
import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.web.search.RecruitSelectionTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface RecruitSelectionTypeService extends IService<RecruitSelectionType> {
    public List<RecruitSelectionType> getByParam(RecruitSelectionTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(RecruitSelectionTypeSearchParameter searchParameter) throws Exception;
    
    public void save(RecruitSelectionType entity, List<RecruitDynamicField> listRecruitDynamicField) throws Exception;
    
    public void update(RecruitSelectionType entity, List<RecruitDynamicField> listRecruitDynamicField) throws Exception;

}
