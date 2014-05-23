/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.entity.WtHoliday;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "wtHolidayDao")
@Lazy
public class WtHolidayDaoImpl extends IDAOImpl<WtHoliday> implements WtHolidayDao {

    @Override
    public Class<WtHoliday> getEntityClass() {
        return WtHoliday.class;
    }

}
