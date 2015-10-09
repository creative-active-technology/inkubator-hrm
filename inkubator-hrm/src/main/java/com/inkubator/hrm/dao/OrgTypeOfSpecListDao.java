/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OrgTypeOfSpec;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.web.search.OrgTypeOfSpecListSearchParameter;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface OrgTypeOfSpecListDao extends IDAO<OrgTypeOfSpecList> {

    public List<OrgTypeOfSpecList> getByParam(OrgTypeOfSpecListSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalOrgTypeOfSpecListByParam(OrgTypeOfSpecListSearchParameter searchParameter);

    public OrgTypeOfSpecList getSpecTypeNameByOrgTypeOfSpecListId(Long id);

    public List<OrgTypeOfSpecList> getOrgTypeOfSpecList(long id);
    
    public List<OrgTypeOfSpecList> getAllDataByOrgTypeOfSpecIdAndOrderByCode(Long id);
    
    public OrgTypeOfSpecList getEntityByPkWithDetail(Long id);

    public void saveAndMerge(OrgTypeOfSpecList orgTypeOfSpecList);
}
