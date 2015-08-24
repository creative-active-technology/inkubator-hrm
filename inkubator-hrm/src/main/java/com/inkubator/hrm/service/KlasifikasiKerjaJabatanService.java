package com.inkubator.hrm.service;


import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface KlasifikasiKerjaJabatanService extends IService<KlasifikasiKerjaJabatan> {
	public List<KlasifikasiKerjaJabatan> getAllDataByJabatanId(Long jabatanId) throws Exception;
}
