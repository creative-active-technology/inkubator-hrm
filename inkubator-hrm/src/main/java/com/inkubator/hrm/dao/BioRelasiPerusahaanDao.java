/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.web.search.BioRelasiPerusahaanSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface BioRelasiPerusahaanDao extends IDAO<BioRelasiPerusahaan> {

    public List<BioRelasiPerusahaan> getByParam(BioRelasiPerusahaanSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(BioRelasiPerusahaanSearchParameter searchParameter);

    public List<BioRelasiPerusahaan> getAllDataByBioDataId(Long bioDataId);
    
    public BioRelasiPerusahaan getEntityByPkWithDetail(Long id);
}
