/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import com.inkubator.hrm.entity.BioSpesifikasiAbilityId;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface BioSpesifikasiAbilityService extends IService<BioSpesifikasiAbility>{
    public BioSpesifikasiAbility getAllDataByPK(Long id) throws Exception;
    
    public List<BioSpesifikasiAbility> getAllDataByBiodataId(Long biodataId) throws Exception;
    
    public BioSpesifikasiAbility getEntityByBioSpesifikasiAbilityId(BioSpesifikasiAbilityId id) throws Exception;
    
    public void updateBioSpecAbility(BioSpesifikasiAbility entity, Long oldId) throws Exception;
    
}
