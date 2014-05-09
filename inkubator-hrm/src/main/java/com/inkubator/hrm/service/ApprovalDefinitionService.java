/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.service;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface ApprovalDefinitionService extends IDAO<ApprovalDefinition>{
    public List<ApprovalDefinition> getByParam(ApprovalDefinitionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalApprovalDefinitionByParam(ApprovalDefinitionSearchParameter searchParameter) throws Exception;
}
