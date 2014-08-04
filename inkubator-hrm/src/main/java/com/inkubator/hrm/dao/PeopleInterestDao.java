/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioPeopleInterest;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface PeopleInterestDao extends IDAO<BioPeopleInterest>{
    public BioPeopleInterest getAllDataByPK(Long id);
    
    public List<BioPeopleInterest> getAllDataByBioDataId(Long bioDataId);
}
