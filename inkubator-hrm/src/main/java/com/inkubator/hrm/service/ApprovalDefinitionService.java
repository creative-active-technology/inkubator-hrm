/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;
import org.primefaces.model.diagram.DefaultDiagramModel;

/**
 *
 * @author Deni Husni FR
 */
public interface ApprovalDefinitionService extends IService<ApprovalDefinition> {

    public List<ApprovalDefinition> getByParam(ApprovalDefinitionSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalApprovalDefinitionByParam(ApprovalDefinitionSearchParameter searchParameter) throws Exception;

    public List<ApprovalDefinition> getAllDataByLoanNewSchemaId(Long id) throws Exception;

    public void updateStatusAndSms(ApprovalDefinition approvalDefinition) throws Exception;

    public DefaultDiagramModel getGraphMode(long id) throws Exception;

    public List<ApprovalDefinition> getALLDataWithSequece(int sequace) throws Exception;
}
