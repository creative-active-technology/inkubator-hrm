/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EducationHistory;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface EducationHistoryDao extends IDAO<EducationHistory>{
    
    public EducationHistory getAllDataByPK(Long id);
    
    public List<EducationHistory> getAllDataByBioDataId(Long bioDataId);
}
