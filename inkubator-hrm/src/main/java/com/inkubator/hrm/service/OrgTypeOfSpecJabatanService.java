/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatanId;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface OrgTypeOfSpecJabatanService extends IService<OrgTypeOfSpecJabatan>{
    public List<OrgTypeOfSpecJabatan> getAllDataByJabatanId(Long id, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalDataByJabatanId(Long id) throws Exception;
    
    
    public void save(OrgTypeOfSpecJabatan entity, List<OrgTypeOfSpecList> listTypeSpec) throws Exception;
    
    public OrgTypeOfSpecJabatan getEntityByPK(OrgTypeOfSpecJabatanId id) throws Exception;
    
    public List<OrgTypeOfSpecJabatan> getAllDataByJabatanId(Long id) throws Exception;
}
