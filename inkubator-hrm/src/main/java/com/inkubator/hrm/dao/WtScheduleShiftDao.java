/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtScheduleShift;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface WtScheduleShiftDao extends IDAO<WtScheduleShift> {

    public List<WtScheduleShift> getByParam(Long workingGroupId, int firstResult, int maxResults, Order order);

    public Long getTotalWtScheduleShiftByParam(Long workingGroupId);

    public void saveAndMerge(WtScheduleShift scheduleShift);

    public void saveBatach(List<WtScheduleShift> dataToBacth);

    public List<WtScheduleShift> getAllByWorkingGroupId(long workingGroupId);

    public WtScheduleShift getBywtGroupWorkingIdAndscheduleDate(long id, Date date);

    public WtScheduleShift getByWtGroupWorkingId(long id);

}
