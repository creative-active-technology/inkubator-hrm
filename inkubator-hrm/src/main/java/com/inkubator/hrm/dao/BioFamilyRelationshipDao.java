package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioFamilyRelationship;

/**
 *
 * @author Taufik hidayat
 */
public interface BioFamilyRelationshipDao extends IDAO<BioFamilyRelationship> {

    public List<BioFamilyRelationship> getAllDataByBioDataId(Long bioDataId);
    
    public BioFamilyRelationship getEntityByPKWithDetail(Long id);

}
