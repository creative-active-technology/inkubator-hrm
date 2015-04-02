package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.MedicalCare;

/**
 *
 * @author Taufik hidayat
 */
public interface MedicalCareDao extends IDAO<MedicalCare> {

    public List<MedicalCare> getByParam(String parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalMedicalCareByParam(String parameter);

    public MedicalCare getEntityWithDetail(Long id);

    public List<MedicalCare> getAllDataWithDetail();
    
    public MedicalCare getEntityWithNameAndNik(Long id);

}
