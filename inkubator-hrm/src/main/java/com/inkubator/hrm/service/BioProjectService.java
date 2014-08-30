package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioProject;

/**
*
* @author Taufik Hidayat
*/
public interface BioProjectService extends IService<BioProject> {

	public List<BioProject> getAllDataByBioDataId(Long bioDataId) throws Exception;
        
        public BioProject getEntityByPKWithDetail(Long id) throws Exception;

}
