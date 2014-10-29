/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CheckInAttendanceDao;
import com.inkubator.hrm.entity.CheckInAttendance;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "checkInAttendanceDao")

public class CheckInAttendanceDaoImpl extends IDAOImpl<CheckInAttendance> implements CheckInAttendanceDao {

    @Override
    public Class<CheckInAttendance> getEntityClass() {
        return CheckInAttendance.class;
    }

}
