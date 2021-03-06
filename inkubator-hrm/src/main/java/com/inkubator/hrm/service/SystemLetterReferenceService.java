/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.SystemLetterReference;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Deni
 */
public interface SystemLetterReferenceService extends IService<SystemLetterReference> {
    public List<SystemLetterReference> getByParam(SystemLetterReferenceSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter searchParameter) throws Exception;
    
    public void save(SystemLetterReference entity, UploadedFile uploadedFile) throws Exception;
    
    public void update(SystemLetterReference entity, UploadedFile uploadedFile) throws Exception;
    
}
