package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioEmploymentHistory;

/**
*
* @author Taufik Hidayat
*/
public interface BioEmploymentHistoryService extends IService<BioEmploymentHistory> {

	public List<BioEmploymentHistory> getAllDataByBioDataId(Long bioDataId) throws Exception;

}
