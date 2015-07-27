/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface ApprovalDefinitionDao extends IDAO<ApprovalDefinition> {

    public List<ApprovalDefinition> getByParam(ApprovalDefinitionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalApprovalDefinitionByParam(ApprovalDefinitionSearchParameter searchParameter);

    public Long getTotalSameAprrovalProsesExist(String approvalName, String procesName, int sequance);

    public Long getTotalApprovalExistWithSequenceOne(String approvalName);

    public Long getTotalDataWithSequenceLower(String approvalName, int sequance);
    
    public Long getTotalDataWithSequenceLowerAndNotId(String approvalName, int sequance, long id);

    public List<ApprovalDefinition> getAllDataByNameAndProcessType(String name, String processType, Order order);
    
    public List<ApprovalDefinition> getAllDataByNameAndProcessTypeAndSpecificName(String name, String processType, String specificName, Order order);

    public List<ApprovalDefinition> getAllDataByNameAndProcessTypeAndSequenceGreater(String name, String processType, int sequence);
    
    public List<ApprovalDefinition> getAllDataByNameAndProcessTypeAndSpecificNameAndSequenceGreater(String name, String processType, String specificName, int sequence);

    public Long getTotalSameAprrovalProsesExistAndNotId(String approvalName, String procesName, int sequance, long id);
    
    public List<ApprovalDefinition> getAllDataByName(String name);
}
