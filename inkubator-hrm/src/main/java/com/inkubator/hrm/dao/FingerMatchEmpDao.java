package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.FingerMatchEmp;

/**
 *
 * @author rizkykojek
 */
public interface FingerMatchEmpDao extends IDAO<FingerMatchEmp> {

	public List<FingerMatchEmp> getAllDataByNik(String nik);
	
	public FingerMatchEmp getEntityByNikAndMachineId(String nik, Long machineId);
	
}

