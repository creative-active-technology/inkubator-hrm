/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.web.search.AttendanceStatusSearchParamater;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface AttendanceStatusDao extends IDAO<AttendanceStatus> {

    public List<AttendanceStatus> getByParam(AttendanceStatusSearchParamater searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalAttendanceStatusyParam(AttendanceStatusSearchParamater searchParameter);

    public Long getTotalDuplicateByName(String statusCode);

    public Long getTotalDuplicaByNameAndNotId(String statusCode, Long id);
}
