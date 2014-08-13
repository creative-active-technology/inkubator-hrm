package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioFamilyRelationship;

/**
*
* @author Taufik Hidayat
*/
public interface BioFamilyRelationshipService extends IService<BioFamilyRelationship> {

	public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId) throws Exception;
        
        public BioFamilyRelationship getEntityByPKWithDetail(Long id) throws Exception;

}
