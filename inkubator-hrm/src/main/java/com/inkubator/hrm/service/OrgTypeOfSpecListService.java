/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.OrgTypeOfSpecListSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.DualListModel;

/**
 *
 * @author EKA
 */
public interface OrgTypeOfSpecListService extends IService<OrgTypeOfSpecList> {

    public List<OrgTypeOfSpecList> getByParam(OrgTypeOfSpecListSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalOrgTypeOfSpecListByParam(OrgTypeOfSpecListSearchParameter searchParameter) throws Exception;

    public OrgTypeOfSpecList getSpecTypeNameByOrgTypeOfSpecListId(Long id) throws Exception;

    public List<DualListModel> getAllBySpectJabatan() throws Exception;

    public List<String> getOrgTypeSpecName() throws Exception;
    
    public List<OrgTypeOfSpecList> getAllDataByOrgTypeOfSpecIdAndOrderByCode(Long id) throws Exception;
    
    public OrgTypeOfSpecList getAllDataWithDetail(Long id) throws Exception;
    
}
