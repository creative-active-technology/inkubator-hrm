/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface TempJadwalKaryawanDao extends IDAO<TempJadwalKaryawan> {

    public List<TempJadwalKaryawan> getAllByEmpId(long empId);

    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId);
    
    public List<TempJadwalKaryawan>getByGroupKerjadId(long kerjaId);
}
