/*
 <<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.web.search.CheckInAttendanceSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface CheckInAttendanceDao extends IDAO<CheckInAttendance> {

    public List<CheckInAttendance> getByParamWithDetail(CheckInAttendanceSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalCheckInAttendanceByParam(CheckInAttendanceSearchParameter searchParameter);

    public CheckInAttendance getByEmpIdAndCheckIn(long id, Date checkInDate);

    public CheckInAttendance getAttendancWithMaxCreatedDate(long id);

	public CheckInAttendance getEntityByEmpDataIdAndCheckDate(Long empDataId, Date checkDate);

}
