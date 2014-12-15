/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.web.search.LoanSchemaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanSchemaService extends IService<LoanSchema>{
    public List<LoanSchema> getAllDataWithAllRelation(LoanSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalByParam(LoanSchemaSearchParameter searchParameter) throws Exception;
    
    public LoanSchema getEntityByPkWithAllRelation(Long id) throws Exception;
    
    public void saveAndNotification(LoanSchema loanSchema) throws Exception;
    
    public List<LoanSchema> getAllDataByEmployeeTypeId(Long empDataId) throws Exception;
    
    public String getLoanSchemaNameByPk(Long id) throws Exception;
}
