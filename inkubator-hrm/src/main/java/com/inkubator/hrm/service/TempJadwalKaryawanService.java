/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface TempJadwalKaryawanService extends IService<TempJadwalKaryawan> {

    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId) throws Exception;
}
