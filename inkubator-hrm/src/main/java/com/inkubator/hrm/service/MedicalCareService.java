package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.web.search.MedicalCareSearchParameter;
import com.inkubator.hrm.web.search.ReportSickDataSearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public interface MedicalCareService extends IService<MedicalCare> {

    public List<MedicalCare> getByParam(MedicalCareSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(MedicalCareSearchParameter parameter) throws Exception;

    public MedicalCare getEntityWithDetail(Long id) throws Exception;

    public List<MedicalCare> getAllDataWithDetail() throws Exception;

    public void save(MedicalCare entity, UploadedFile documentFile) throws Exception;

    public void update(MedicalCare entity, UploadedFile documentFile) throws Exception;
    
    public MedicalCare getEntityWithNameAndNik(Long id) throws Exception;
    
    public List<MedicalCare> getByParamForReportSickData(ReportSickDataSearchParameter searchParameter,int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalByParamForReportSickData(ReportSickDataSearchParameter searchParameter) throws Exception;

}
