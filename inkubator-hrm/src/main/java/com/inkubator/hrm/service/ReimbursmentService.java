/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.web.search.ReimbursmentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface ReimbursmentService extends IService<Reimbursment>{
    public List<Reimbursment> getAllDataWithDetail(ReimbursmentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalReimbursmentByParam(ReimbursmentSearchParameter searchParameter) throws Exception;
    
    public Reimbursment getEntityByPkWithDetail(Long id) throws Exception;
}
