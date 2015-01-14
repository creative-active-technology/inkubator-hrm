/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface PayTempAttendanceStatusService extends IService<PayTempAttendanceStatus>{
    public List<PayTempAttendanceStatus> getByParam(String parameter, PayTempAttendanceStatusModel payTempAttendanceStatusModel, int firstResult, int maxResults, Order order) throws Exception;

    public List<PayTempAttendanceStatus> getByWtPeriodeWhereComponentPayrollIsActive(PayTempAttendanceStatusModel payTempAttendanceStatusModel) throws Exception;
    
    public Long getTotalResourceTypeByParam(String parameter, PayTempAttendanceStatusModel payTempAttendanceStatusModel) throws Exception;
}
