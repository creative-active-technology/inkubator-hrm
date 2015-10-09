package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasi;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;

public interface OrgTypeOfSpecListKlasifikasiDao extends IDAO<OrgTypeOfSpecListKlasifikasi>{
	public List<OrgTypeOfSpecListKlasifikasi> getByorgTypeOfSpecListId(Long id);
}