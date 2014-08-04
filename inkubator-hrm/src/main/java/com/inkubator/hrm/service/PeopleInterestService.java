/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PeopleInterest;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface PeopleInterestService extends IService<PeopleInterest>{
    public PeopleInterest getAllDataByPK(Long id);
    
    public List<PeopleInterest> getAllDataByBioDataId(Long bioDataId) throws Exception;
}
