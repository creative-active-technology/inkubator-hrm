package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioEmploymentHistory;

/**
 *
 * @author Taufik hidayat
 */
public interface BioEmploymentHistoryDao extends IDAO<BioEmploymentHistory> {

    public List<BioEmploymentHistory> getAllDataByBioDataId(Long bioDataId);

}
