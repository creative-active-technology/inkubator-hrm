/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.JabatanSpesifikasiId;
import java.util.List;

/**
 *
 * @author Deni Husni FR
 */
public interface JabatanSpesifikasiDao extends IDAO<JabatanSpesifikasi>{

    public JabatanSpesifikasi getDataByPK(Long id);
    
    public List<JabatanSpesifikasi> getAllDataByJabatanId(Long jabatanId);
    
    public JabatanSpesifikasi getEntityByBioJabatanSpesifikasiId(JabatanSpesifikasiId id);
    
    public Long getTotalEntityByBioJabatanSpesifikasiId(JabatanSpesifikasiId id);
    
    public Long getTotalEntityByBioJabatanAndSpesifikasiId(Long jabatanId, Long specId, JabatanSpesifikasiId id);
}
