/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioRelasiPerusahaan;
import com.inkubator.hrm.web.search.BioRelasiPerusahaanSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Deni
 */
public interface BioRelasiPerusahaanService extends IService<BioRelasiPerusahaan> {
    public List<BioRelasiPerusahaan> getByParam(BioRelasiPerusahaanSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(BioRelasiPerusahaanSearchParameter searchParameter) throws Exception;
    
    public List<BioRelasiPerusahaan> getAllDataByBioDataId(Long bioDataId) throws Exception;
    
    public void save(BioRelasiPerusahaan entity, UploadedFile fileUpload) throws Exception;
    
    public void update(BioRelasiPerusahaan entity, UploadedFile fileUpload) throws Exception;
    
    public BioRelasiPerusahaan getEntityByPkWithDetail(Long id) throws Exception;
}
