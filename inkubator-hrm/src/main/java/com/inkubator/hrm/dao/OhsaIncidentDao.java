/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.OhsaIncident;
import com.inkubator.hrm.web.search.OhsaIncidentSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface OhsaIncidentDao extends IDAO<OhsaIncident> {
    public List<OhsaIncident> getByParam(OhsaIncidentSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(OhsaIncidentSearchParameter parameter);
    
    public OhsaIncident getEntityByPKWithDetail(Long id);
    
}
