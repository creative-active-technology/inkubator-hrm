/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.web.search.DivisiSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface DivisiDao extends IDAO<Divisi>{
    public List<Divisi> getByParam(DivisiSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalDivisiByParam(DivisiSearchParameter searchParameter);
    
    public Divisi getDepartmentNameByDivisiId(Long id);
}
