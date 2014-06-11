/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.WtScheduleShift;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtScheduleShiftService extends IService<WtScheduleShift> {

    public List<WtScheduleShift> getByParam(Long workingGroupId, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalWtScheduleShiftByParam(Long workingGroupId) throws Exception;
}
