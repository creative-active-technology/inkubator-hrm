/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface BioSpesifikasiAbilityDao extends IDAO<BioSpesifikasiAbility>{

    public BioSpesifikasiAbility getAllDataByPK(Long id);
    
    public List<BioSpesifikasiAbility> getAllDataByBiodataId(Long bioDataId);
    
    public BioSpesifikasiAbility getEntityByBioSpesifikasiAbilityId(BioSpesifikasiAbilityId id);
    
    public Long getTotalEntityByBioBioSpesifikasiAbilityId(BioSpesifikasiAbilityId id);
}
