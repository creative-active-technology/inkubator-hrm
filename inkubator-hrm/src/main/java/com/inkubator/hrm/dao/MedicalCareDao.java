package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.web.search.MedicalCareSearchParameter;

/**
 *
 * @author Taufik hidayat
 */
public interface MedicalCareDao extends IDAO<MedicalCare> {

    public List<MedicalCare> getByParam(MedicalCareSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalMedicalCareByParam(MedicalCareSearchParameter parameter);

    public MedicalCare getEntityWithDetail(Long id);

    public List<MedicalCare> getAllDataWithDetail();

}
