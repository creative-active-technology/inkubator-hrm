/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.web.search.BioPotensiSwotSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface BioPotensiSwotDao extends IDAO<BioPotensiSwot> {
    public List<BioPotensiSwot> getByParam(BioPotensiSwotSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(BioPotensiSwotSearchParameter searchParameter);
    
    public List<BioPotensiSwot> getAllDataByBioDataId(Long bioDataId);
}
