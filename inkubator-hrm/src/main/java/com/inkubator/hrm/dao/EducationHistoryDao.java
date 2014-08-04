/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioEducationHistory;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface EducationHistoryDao extends IDAO<BioEducationHistory>{
    
    public BioEducationHistory getAllDataByPK(Long id);
    
    public List<BioEducationHistory> getAllDataByBioDataId(Long bioDataId);
}
