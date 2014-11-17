package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioFamilyRelationship;
import com.inkubator.hrm.web.model.FamilyRelationhipReportModel;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;

/**
 *
 * @author Taufik hidayat
 */
public interface BioFamilyRelationshipDao extends IDAO<BioFamilyRelationship> {

    public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId);
    
    public BioFamilyRelationship getEntityByPKWithDetail(Long id);
    
    public List<BioFamilyRelationship> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable);
    
    public List<FamilyRelationhipReportModel> getAllDataReportOfEmployeesFamilyModelByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable);
    
    public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter);

}
