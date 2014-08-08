/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.web.model.BioEducationHistoryViewModel;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface EducationHistoryService extends IService<BioEducationHistory>{
    public BioEducationHistory getAllDataByPK(Long id) throws Exception;
    
    public BioEducationHistoryViewModel getAllByPKByController(Long id) throws Exception;
    
    public List<BioEducationHistoryViewModel> getAllDataByBioDataId(Long bioDataId) throws Exception;
    
}
