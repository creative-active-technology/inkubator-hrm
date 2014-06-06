/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtScheduleShiftDao;
import com.inkubator.hrm.entity.WtScheduleShift;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "wtScheduleShiftDao")
@Lazy
public class WtScheduleShiftDaoImpl extends IDAOImpl<WtScheduleShift> implements WtScheduleShiftDao {

    @Override
    public Class<WtScheduleShift> getEntityClass() {
        return WtScheduleShift.class;
    }

}
