/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PeopleInterest;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface PeopleInterestDao extends IDAO<PeopleInterest>{
    public PeopleInterest getAllDataByPK(Long id);
    
    public List<PeopleInterest> getAllDataByBioDataId(Long bioDataId);
}
