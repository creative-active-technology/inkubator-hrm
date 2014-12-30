/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtPeriodeService extends IService<WtPeriode> {

    public List<WtPeriode> getByParam(WtPeriodeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalWtPeriodeByParam(WtPeriodeSearchParameter searchParameter) throws Exception;
    
    public WtPeriode getEntityByPayrollTypeActive() throws Exception;
    
    public WtPeriode getEntityByAbsentTypeActive() throws Exception;
    
    public List<WtPeriode> getAllYears() throws Exception;
}
