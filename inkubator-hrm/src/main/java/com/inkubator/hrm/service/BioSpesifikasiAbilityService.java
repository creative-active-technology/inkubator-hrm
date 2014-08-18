/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioSpesifikasiAbility;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface BioSpesifikasiAbilityService extends IService<BioSpesifikasiAbility>{
    public BioSpesifikasiAbility getAllDataByPK(Long id) throws Exception;
    
    public List<BioSpesifikasiAbility> getAllDataByBiodataId(Long biodataId) throws Exception;
}
