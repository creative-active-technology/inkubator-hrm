/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.web.search.LoanNewSchemaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanNewSchemaService extends IService<LoanNewSchema> {

    public List<LoanNewSchema> getAllDataByParam(LoanNewSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalDataByParam(LoanNewSchemaSearchParameter searchParameter) throws Exception;

    public void save(LoanNewSchema entity, List<ApprovalDefinition> appDefs) throws Exception;

    public void update(LoanNewSchema entity, List<ApprovalDefinition> appDefs) throws Exception;
}
