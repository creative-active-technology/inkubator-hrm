/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.web.model.JobJabatanModel;
import com.inkubator.hrm.web.model.KompetensiJabatanViewModel;
import com.inkubator.hrm.web.model.PerformanceIndicatorJabatanViewModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.hrm.web.search.KompetensiJabatanSearchParameter;
import com.inkubator.hrm.web.search.PerformanceIndicatorJabatanSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanService extends IService<Jabatan> {

    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter) throws Exception;

    public Jabatan getJabatanByLevelOne(Integer level) throws Exception;

    public List<Jabatan> getJabatanByParentCode(String parentCode) throws Exception;

    public Jabatan getJabatanByIdWithDetail(Long id) throws Exception;
    
    public Jabatan getJabatanByIdForSpecDetail(Long id) throws Exception;

    public List<Jabatan> getJabatansByLevel(Integer level) throws Exception;

    public Jabatan getByIdWithJobDeskripsi(long id) throws Exception;

    public Jabatan getByIdWithKlasifikasiKerja(long id) throws Exception;

    public List<Jabatan> getByDepartementId(long id) throws Exception;
    
    public void updateForSalaryGrade(Jabatan entity) throws Exception;
    
    public Jabatan getByIdWithSalaryGrade(long id) throws Exception;
    
    public List<Jabatan>getByName(String name)throws Exception;

	public List<Jabatan> getAllDataByCodeOrName(String param) throws Exception;
	
	public void saveDataJabatan(JobJabatanModel jobJabatanModel) throws Exception;
	
	public Jabatan getJabatanByCode(String code) throws Exception;
	
	public void updateDataJabatan(JobJabatanModel jobJabatanModel) throws Exception;
	
	public List<KompetensiJabatanViewModel> getByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

	public Long getTotalByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter) throws Exception;

	public List<PerformanceIndicatorJabatanViewModel> getByParamForPerformanceIndicatorJabatan(PerformanceIndicatorJabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

	public Long getTotalByParamForPerformanceIndicatorJabatan(PerformanceIndicatorJabatanSearchParameter searchParameter) throws Exception;
}
