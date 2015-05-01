/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatan;
import com.inkubator.hrm.entity.OrgTypeOfSpecJabatanId;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface OrgTypeOfSpecJabatanDao extends IDAO<OrgTypeOfSpecJabatan> {

    public List<OrgTypeOfSpecJabatan> getAllDataByJabatanId(Long id, int firstResult, int maxResults, Order order);

    public Long getTotalDataByJabatanId(Long id);
    
    public OrgTypeOfSpecJabatan getEntityByPK(OrgTypeOfSpecJabatanId id);
    
    public Long getTotalByPK(OrgTypeOfSpecJabatanId id);
}
