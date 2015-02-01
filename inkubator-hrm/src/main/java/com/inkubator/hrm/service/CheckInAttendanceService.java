/*
 <<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.web.search.CheckInAttendanceSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface CheckInAttendanceService extends IService<CheckInAttendance> {

    public List<CheckInAttendance> getByParamWithDetail(CheckInAttendanceSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalCheckInAttendanceByParam(CheckInAttendanceSearchParameter searchParameter) throws Exception;

    public CheckInAttendance getByEmpIdAndCheckIn(long id, Date checkInDate) throws Exception;

    public CheckInAttendance getAttendancWithMaxCreatedDate(long id) throws Exception;

}
