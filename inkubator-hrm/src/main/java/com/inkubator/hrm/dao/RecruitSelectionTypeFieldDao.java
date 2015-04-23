/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionTypeField;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface RecruitSelectionTypeFieldDao extends IDAO<RecruitSelectionTypeField>{
    public List<RecruitSelectionTypeField> getEntityByRecruitSelectionTypeId(Long id);
    
    public void deleteAllDataBySelectionTypeId(Long id);
}
