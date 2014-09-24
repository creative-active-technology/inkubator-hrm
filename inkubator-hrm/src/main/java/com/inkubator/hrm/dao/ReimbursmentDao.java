/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.web.search.ReimbursmentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ReimbursmentDao extends IDAO<Reimbursment>{
    public List<Reimbursment> getAllDataWithDetail(ReimbursmentSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalReimburstByParam(ReimbursmentSearchParameter searchParameter);
    
    public Reimbursment getEntityByPkWithDetail(Long id);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public Long getByCode(String code);
    
    public List<Reimbursment> getAllDataWithEmpIdAndReimbursmentSchemaId(Long empId, Long reimbursmentSchemaId);
    
    public Reimbursment getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber);
    
    public Reimbursment getEntityByReimbursmentNoWithDetail(String reimburmentNo);
}
