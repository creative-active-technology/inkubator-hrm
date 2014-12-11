package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.MedicalCare;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Taufik Hidayat
 */
public interface MedicalCareService extends IService<MedicalCare> {

    public List<MedicalCare> getByParam(String parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(String parameter) throws Exception;

    public MedicalCare getEntityWithDetail(Long id) throws Exception;

    public List<MedicalCare> getAllDataWithDetail() throws Exception;

    public void save(MedicalCare entity, UploadedFile documentFile) throws Exception;

    public void update(MedicalCare entity, UploadedFile documentFile) throws Exception;

}