/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.web.search.HolidaySearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtHolidayService extends IService<WtHoliday> {

    public List<WtHoliday> getByParam(HolidaySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalWtHolidayByParam(HolidaySearchParameter searchParameter) throws Exception;

    public List<WtHoliday> getBetweenDate(Date start, Date end) throws Exception;
    
    public WtHoliday getEntityByDate(Date date)  throws Exception;
}
