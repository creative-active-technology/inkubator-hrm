/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioPeopleInterest;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface BioPeopleInterestService extends IService<BioPeopleInterest>{
    public BioPeopleInterest getAllDataByPK(Long id);
    
    public List<BioPeopleInterest> getAllDataByBioDataId(Long bioDataId) throws Exception;
}
