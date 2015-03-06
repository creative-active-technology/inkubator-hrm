/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioPotensiSwot;
import com.inkubator.hrm.web.search.BioPotensiSwotSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface BioPotensiSwotService extends IService<BioPotensiSwot> {

    public List<BioPotensiSwot> getByParam(BioPotensiSwotSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(BioPotensiSwotSearchParameter searchParameter) throws Exception;
    
    public List<BioPotensiSwot> getAllDataByBioDataId(Long bioDataId) throws Exception;
}
