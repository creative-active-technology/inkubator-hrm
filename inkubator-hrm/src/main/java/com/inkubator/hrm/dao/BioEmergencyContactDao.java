/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioEmergencyContact;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface BioEmergencyContactDao extends IDAO<BioEmergencyContact>{
     public List<BioEmergencyContact> getAllDataByBioDataId(long id);
     
     public BioEmergencyContact getEntityByPKWithDetail(long id);
     
     public List<BioEmergencyContact> getAllDataWithDetailByBioDataId(long bioDataId);
}
