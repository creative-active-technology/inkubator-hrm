/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.web.search.DivisiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface DivisiService extends IService<Divisi>{
    public List<Divisi> getByParam(DivisiSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalDivisiByParam(DivisiSearchParameter searchParameter) throws Exception;
    
    public Divisi getDepartmentNameByDivisiId(Long id);
}
