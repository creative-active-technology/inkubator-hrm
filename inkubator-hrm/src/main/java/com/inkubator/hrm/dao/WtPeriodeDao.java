/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.web.search.WtPeriodeSearchParameter;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtPeriodeDao extends IDAO<WtPeriode> {

    public List<WtPeriode> getByParam(WtPeriodeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalWtPeriodeByParam(WtPeriodeSearchParameter searchParameter);

    public WtPeriode getEntityByPayrollTypeActive();

    public WtPeriode getEntityByAbsentTypeActive();

    public List<WtPeriode> getAllYears();

    public WtPeriode getEntityByMonthAndYear(String month, String year);

    public WtPeriode getEntityByFromPeriodeAndUntilPeriode(Date fromPeriode, Date untilPeriode);
    
    public WtPeriode getEntityByDateBetween(Date date);

}
