package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.web.search.KlasifikasiKerjaSearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface KlasifikasiKerjaDao extends IDAO<KlasifikasiKerja> {

	public List<KlasifikasiKerja> getByParam(KlasifikasiKerjaSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalKlasifikasiKerjaByParam(KlasifikasiKerjaSearchParameter parameter);
	
	public Long getTotalByCode(String name);
	
	public Long getTotalByCodeAndNotId(String code, Long id);

}
