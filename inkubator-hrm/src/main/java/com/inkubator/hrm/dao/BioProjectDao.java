package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioProject;

/**
 *
 * @author Taufik hidayat
 */
public interface BioProjectDao extends IDAO<BioProject> {

    public List<BioProject> getAllDataByBioDataId(Long bioDataId);
    
    public BioProject getEntityByPKWithDetail(Long id);
    

}
