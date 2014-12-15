/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.ReimbursmentSchemaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ReimbursmentSchemaService extends IService<ReimbursmentSchema>{
    public List<ReimbursmentSchema> getAllDataWithAllRelation(ReimbursmentSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalReimbursmentByParam(ReimbursmentSchemaSearchParameter searchParameter) throws Exception;
    
    public ReimbursmentSchema getEntityByPkWithAllRelation(Long id) throws Exception;
    
    public void saveAndNotification(ReimbursmentSchema reimbursmentSchema) throws Exception;
    
    public String getReimbursmentSchemaNameByPk(Long id) throws Exception;
}
