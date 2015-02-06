/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.web.search.EmpRotasiSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpRotasiService extends IService<EmpRotasi> {

    public List<EmpRotasi> getByParam(EmpRotasiSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalEmpDataByParam(EmpRotasiSearchParameter searchParameter) throws Exception;
    
}
