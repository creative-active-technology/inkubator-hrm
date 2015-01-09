/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.ReimbursmentSchemaSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ReimbursmentSchemaDao extends IDAO<ReimbursmentSchema> {

    public List<ReimbursmentSchema> getAllDataWithAllRelation(ReimbursmentSchemaSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalReimbursmentByParam(ReimbursmentSchemaSearchParameter searchParameter);

    public ReimbursmentSchema getEntityByPkWithAllRelation(Long id);

    public Long getTotalByCodeAndNotId(String code, Long id);

    public Long getByReimbursmentCode(String code);

    public void saveAndMerge(ReimbursmentSchema reimbursmentSchema);

    public List<ReimbursmentSchema> isPayrollComponent(Long id);
    
    public String getReimbursmentSchemaNameByPk(Long id) throws Exception;
}
