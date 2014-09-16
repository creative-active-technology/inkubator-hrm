/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "tempJadwalKaryawanDao")
@Lazy
public class TempJadwalKaryawanDaoImpl extends IDAOImpl<TempJadwalKaryawan> implements TempJadwalKaryawanDao {

    @Override
    public Class<TempJadwalKaryawan> getEntityClass() {
        return TempJadwalKaryawan.class;
    }

}
