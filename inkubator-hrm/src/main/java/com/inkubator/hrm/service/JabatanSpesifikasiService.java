/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.JabatanSpesifikasiId;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface JabatanSpesifikasiService extends IService<JabatanSpesifikasi> {

    public JabatanSpesifikasi getDataByPK(Long id) throws Exception;
    
    public List<JabatanSpesifikasi> getAllDataByJabatanId(Long jabatanId) throws Exception;
    
    public void save(Long id, List<EducationLevel> educationLevels, List<Major> majorLevel, List<Faculty> faculty, List<OccupationType> occupation) throws Exception;
    
    public void update(Long id, List<EducationLevel> educationLevels, List<Major> majorLevel, List<Faculty> faculty, List<OccupationType> occupation) throws Exception;

    public void update(JabatanSpesifikasi entity, Long oldId) throws Exception;
    
    public JabatanSpesifikasi getEntityByBioJabatanSpesifikasiId(JabatanSpesifikasiId id) throws Exception;
}
