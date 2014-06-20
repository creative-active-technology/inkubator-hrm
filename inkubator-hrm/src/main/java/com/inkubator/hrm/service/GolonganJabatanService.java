/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.web.search.GolonganJabatanSearchParameter;

/**
 *
 * @author Deni Husni FR,rizkykojek
 */
public interface GolonganJabatanService extends IService<GolonganJabatan>{
    
	public List<GolonganJabatan> getByParam(GolonganJabatanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(GolonganJabatanSearchParameter parameter) throws Exception;

	public GolonganJabatan getEntityByPkFetchAttendPangkat(Long id) throws Exception;
}
