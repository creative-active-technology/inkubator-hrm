/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface TempJadwalKaryawanService extends IService<TempJadwalKaryawan>, BaseApprovalService {

    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId) throws Exception;

    public void savePenempatanJadwal(EmpData empData) throws Exception;

    public void saveMassPenempatanJadwal(List<Long> data, long groupWorkingId, Date createdOn, String createdBy) throws Exception;

    public String saveMassPenempatanJadwal(List<EmpData> data, long groupWorkingId) throws Exception;

    public TempJadwalKaryawan getEntityByEmpDataIdAndTanggalWaktuKerja(Long id, Date implementationDate) throws Exception;

    public TempJadwalKaryawan getByEmpId(Long id, Date implementationDate) throws Exception;
    
    public List<TempJadwalKaryawan> getAllByEmpIdWithDetailWithFromAndUntilPeriod(long empId) throws Exception;
}
