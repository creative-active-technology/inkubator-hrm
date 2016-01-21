/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.web.model.KompetensiJabatanViewModel;
import com.inkubator.hrm.web.model.PerformanceIndicatorJabatanViewModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.hrm.web.search.KompetensiJabatanSearchParameter;
import com.inkubator.hrm.web.search.PerformanceIndicatorJabatanSearchParameter;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanDao extends IDAO<Jabatan> {

    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter);

    public Jabatan getJabatanByLevelOne(Integer level);

    public List<Jabatan> getJabatanByParentCode(String parentCode);

    public Jabatan getJabatanByIdWithDetail(Long id);

    public List<Jabatan> getJabatansByLevel(Integer level);

    public Jabatan getByIdWithJobDeskripsi(long id) throws Exception;

    public void saveAndMerge(Jabatan jabatan);

    public List<Jabatan> getByDepartementId(long id);

    public Jabatan getByIdWithSalaryGrade(long id);

    public List<Jabatan> getByName(String name);

	public List<Jabatan> getAllDataByCodeOrName(String param);
	
	public Jabatan getJabatanByCode(String code);
	
	public List<KompetensiJabatanViewModel> getByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order);

	public Long getTotalByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter);

	public List<PerformanceIndicatorJabatanViewModel> getByParamForPerformanceIndicatorJabatan(PerformanceIndicatorJabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order);

	public Long getTotalByParamForPerformanceIndicatorJabatan(PerformanceIndicatorJabatanSearchParameter searchParameter);
}
