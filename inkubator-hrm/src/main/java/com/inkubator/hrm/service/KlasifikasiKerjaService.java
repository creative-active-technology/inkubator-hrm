package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.web.search.KlasifikasiKerjaSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface KlasifikasiKerjaService extends IService<KlasifikasiKerja> {

	public List<KlasifikasiKerja> getByParam(KlasifikasiKerjaSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(KlasifikasiKerjaSearchParameter parameter) throws Exception;

}
