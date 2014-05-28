/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.web.search.HolidaySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtHolidayDao extends IDAO<WtHoliday> {

    public List<WtHoliday> getByParam(HolidaySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalWtHolidayByParam(HolidaySearchParameter searchParameter);

    public Long getTotalWtHolidayByHolidayName(String holidayName);

    public Long getTotalWtHolidayByNameAndNotId(String holidayName, Long id);
}
