/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.web.search.LoanSchemaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface LoanSchemaDao extends IDAO<LoanSchema>{
    public List<LoanSchema> getAllDataWithAllRelation(LoanSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalByParam(LoanSchemaSearchParameter searchParameter);
    
    public LoanSchema getEntityByPkWithAllRelation(Long id);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public Long getByCode(String code);
    
    public void saveAndMerge(LoanSchema loanSchema);

	public List<LoanSchema> getAllDataByEmployeeTypeId(Long employeeTypeId);
}
