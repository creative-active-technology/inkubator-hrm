package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;

/**
*
* @author Taufik Hidayat
*/
public interface BioFamilyRelationshipService extends IService<BioFamilyRelationship> {

	public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId) throws Exception;
        
        public BioFamilyRelationship getEntityByPKWithDetail(Long id) throws Exception;
        
        public List<BioFamilyRelationship> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;
    
        public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter) throws Exception;


}
