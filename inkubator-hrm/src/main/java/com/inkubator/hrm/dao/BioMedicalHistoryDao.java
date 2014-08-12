package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioMedicalHistory;

/**
 *
 * @author Taufik hidayat
 */
public interface BioMedicalHistoryDao extends IDAO<BioMedicalHistory> {

    public List<BioMedicalHistory> getAllDataByBioDataId(Long bioDataId);

}
