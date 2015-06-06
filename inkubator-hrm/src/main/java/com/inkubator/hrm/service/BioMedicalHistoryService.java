package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioMedicalHistory;

/**
*
* @author Taufik Hidayat
*/
public interface BioMedicalHistoryService extends IService<BioMedicalHistory> {

	public List<BioMedicalHistory> getAllDataByBioDataId(Long bioDataId) throws Exception;
	
	public BioMedicalHistory getEntityByPkWithDetail(Long id) throws Exception;

}
