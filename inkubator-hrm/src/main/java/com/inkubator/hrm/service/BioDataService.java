/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;
import org.springframework.web.multipart.MultipartFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.web.search.BioDataSearchParameter;

/**
 *
 * @author Deni Husni FR
 */
public interface BioDataService extends IService<BioData>, BaseApprovalService {

    public List<BioData> getByParam(BioDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(BioDataSearchParameter parameter) throws Exception;

    public BioData getEntityByPKWithDetail(long id) throws Exception;

    public List<BioData> getByName(String name) throws Exception;

    public StreamedContent generateCV(long id) throws Exception;
    
    public void updatePhoto(String nik, MultipartFile file) throws Exception;
    
    public void updateSignature(String nik, MultipartFile signatureFile) throws Exception;

	public void updateFingerPrint(String nik, MultipartFile fingerPrintFile) throws Exception;
	
	public String saveBiodataRevisionWithApproval(Object modifiedEntity, String dataType, EmpData empData) throws Exception;
    
}
