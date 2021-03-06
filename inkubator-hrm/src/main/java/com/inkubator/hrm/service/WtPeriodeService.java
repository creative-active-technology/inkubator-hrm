/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.model.WtPeriodEmpViewModel;
import com.inkubator.hrm.web.search.WtPeriodeEmpSearchParameter;
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
    
    public WtPeriode getEntityByPreviousPayrollTypeActive() throws Exception;
    
    public List<WtPeriode> getAllYears() throws Exception;
    
    public WtPeriode getEntityByMonthAndYear(Integer month, String year) throws Exception;
    
    public List<WtPeriodEmpViewModel> getListWtPeriodEmpByParam(WtPeriodeEmpSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalListWtPeriodEmpByParam(WtPeriodeEmpSearchParameter searchParameter) throws Exception;
    
    public WtPeriodEmpViewModel getWtPeriodEmpByWtPeriodId(Long wtPeriodId) throws Exception;
    
    public List<WtPeriode>getAllWithStatusAbsen(String status) throws Exception;
    
}
