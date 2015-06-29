/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempJadwalKaryawan;

/**
 *
 * @author Deni Husni FR
 */
public interface TempJadwalKaryawanDao extends IDAO<TempJadwalKaryawan> {

    public List<TempJadwalKaryawan> getAllByEmpId(long empId);

    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId);
    
    public List<TempJadwalKaryawan> getAllByEmpIdWithDetailWithFromAndUntilPeriod(long empId, Date from, Date until);
    
    public List<TempJadwalKaryawan> getByGroupKerjadId(long kerjaId);

    public void deleteBacth(List<TempJadwalKaryawan> jadwalKaryawans);

    public void saveBatch(List<TempJadwalKaryawan> jadwalKaryawans);

    public List<TempJadwalKaryawan> getAllByMaxEndDate(Date date);

    public void saveOrUpdateAndMerge(TempJadwalKaryawan jadwalKaryawan);

    public TempJadwalKaryawan getEntityByEmpDataIdAndTanggalWaktuKerja(Long id, Date implementationDate);

    public List<TempJadwalKaryawan> getAllDataByEmpIdAndPeriodDate(Long empDataId, Date startDate, Date endDate);

    public TempJadwalKaryawan getByEmpId(Long id, Date implementationDate);
    
    public List<TempJadwalKaryawan> getByMonthDif(int value);

	public List<TempJadwalKaryawan> getAllDataByEmpIdAndPeriodDateAndNotOffDay(Long empDataid, Date startDate, Date endDate);
	
	public List<TempJadwalKaryawan> getAllDataByEmpIdAndPeriodDateAndOffDay(Long empDataid, Date startDate, Date endDate);

	public Long getTotalByTanggalWaktuKerja(Date date, Long companyId);

}
