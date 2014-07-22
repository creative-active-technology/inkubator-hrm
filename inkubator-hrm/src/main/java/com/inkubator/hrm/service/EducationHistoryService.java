/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EducationHistory;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface EducationHistoryService extends IService<EducationHistory>{
    public EducationHistory getAllDataByPK(Long id);
    
    public List<EducationHistory> getAllDataByBioDataId(Long bioDataId) throws Exception;
}
